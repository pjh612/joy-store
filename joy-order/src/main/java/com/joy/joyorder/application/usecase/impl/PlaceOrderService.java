package com.joy.joyorder.application.usecase.impl;

import com.joy.joyorder.application.client.CouponClient;
import com.joy.joyorder.application.client.ItemClient;
import com.joy.joyorder.application.client.dto.ItemResponse;
import com.joy.joyorder.application.usecase.PlaceOrderUseCase;
import com.joy.joyorder.application.usecase.dto.ConfirmOrderRequest;
import com.joy.joyorder.application.usecase.dto.ConfirmOrderResponse;
import com.joy.joyorder.application.usecase.dto.CreateProvisionalOrderRequest;
import com.joy.joyorder.application.usecase.dto.CreateProvisionalOrderResponse;
import com.joy.joyorder.application.usecase.event.PlaceOrderEvent;
import com.joy.joyorder.domain.models.Coupon;
import com.joy.joyorder.domain.models.Item;
import com.joy.joyorder.domain.models.Order;
import com.joy.joyorder.domain.models.OrderItem;
import com.joy.joyorder.domain.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceOrderService implements PlaceOrderUseCase {
    private final OrderRepository orderRepository;
    private final ItemClient itemClient;
    private final CouponClient couponClient;
    private final ApplicationEventPublisher publisher;

    @Override
    @Transactional
    public ConfirmOrderResponse confirmOrder(ConfirmOrderRequest request) {
        Order order = orderRepository.findById(request.orderId())
                .orElseThrow(() -> new EntityNotFoundException("주문 정보를 찾을 수 없습니다."));
        order.completeOrder();
        if (!order.equalsToTotalPayPrice(request.amount())) {
            throw new IllegalArgumentException("결제 금액이 주문 금액과 일치하지 않습니다.");
        }

        List<UUID> itemIds = order.getOrderItems()
                .stream()
                .map(OrderItem::getItemId)
                .toList();
        List<UUID> sellerIds = itemClient.findAllByIdIn(itemIds)
                .stream()
                .map(ItemResponse::sellerId)
                .toList();
        List<UUID> couponIds = order.getOrderItems()
                .stream()
                .map(OrderItem::getCouponId)
                .toList();

        couponClient.useCoupons(couponIds);
        orderRepository.save(order);

        PlaceOrderEvent event = new PlaceOrderEvent(sellerIds, order.getId());
        publisher.publishEvent(event);

        return new ConfirmOrderResponse(order.getId());
    }

    @Override
    @Transactional
    public CreateProvisionalOrderResponse createProvisionalOrder(CreateProvisionalOrderRequest request) {
        Map<UUID, Integer> orderItemMap = request.orderItems()
                .stream()
                .collect(Collectors.toMap(CreateProvisionalOrderRequest.OrderItemRequestDto::itemId, CreateProvisionalOrderRequest.OrderItemRequestDto::quantity));
        Map<UUID, UUID> orderItemCouponIdMap = request.orderItems()
                .stream()
                .filter(item -> item.couponId() != null)
                .collect(Collectors.toMap(CreateProvisionalOrderRequest.OrderItemRequestDto::itemId, CreateProvisionalOrderRequest.OrderItemRequestDto::couponId));
        Map<UUID, Item> itemMap = itemClient.findAllByIdIn(orderItemMap.keySet())
                .stream()
                .map(it -> new Item(it.id(), it.title(), it.sellerId(), it.price()))
                .collect(Collectors.toMap(Item::getId, Function.identity()));
        Map<UUID, Coupon> orderItemCouponMap = couponClient.findByCouponIdIn(orderItemCouponIdMap.keySet())
                .stream()
                .map(it -> new Coupon(it.id(), Coupon.CouponStatus.valueOf(it.status()), Coupon.CouponType.valueOf(it.type()), it.value()))
                .collect(Collectors.toMap(Coupon::getId, Function.identity()));

        Order order = Order.createNew(request.buyerId());
        for (CreateProvisionalOrderRequest.OrderItemRequestDto orderItem : request.orderItems()) {
            Item item = itemMap.get(orderItem.itemId());
            BigDecimal discountPrice = BigDecimal.ZERO;
            UUID couponId = null;
            if (orderItemCouponMap.containsKey(orderItem.couponId())) {
                Coupon coupon = orderItemCouponMap.get(orderItem.couponId());
                couponId = coupon.getId();
                discountPrice = coupon.getDiscountAmount(item.getPrice().multiply(BigDecimal.valueOf(orderItem.quantity())));
            }
            order.addOrderItem(orderItem.itemId(), item.getItemName(), item.getSellerId(), couponId, item.getPrice(), orderItem.quantity(), discountPrice);
        }

        Order savedOrder = orderRepository.save(order);

        return CreateProvisionalOrderResponse.of(savedOrder);
    }
}
