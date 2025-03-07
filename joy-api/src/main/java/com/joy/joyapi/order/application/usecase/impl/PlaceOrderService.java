package com.joy.joyapi.order.application.usecase.impl;

import com.joy.joyapi.coupon.domain.model.AbstractCoupon;
import com.joy.joyapi.coupon.domain.repository.CouponRepository;
import com.joy.joyapi.item.domain.models.Item;
import com.joy.joyapi.item.domain.repository.ItemRepository;
import com.joy.joyapi.order.application.usecase.PlaceOrderUseCase;
import com.joy.joyapi.order.application.usecase.dto.ConfirmOrderRequest;
import com.joy.joyapi.order.application.usecase.dto.ConfirmOrderResponse;
import com.joy.joyapi.order.application.usecase.dto.CreateProvisionalOrderRequest;
import com.joy.joyapi.order.application.usecase.dto.CreateProvisionalOrderResponse;
import com.joy.joyapi.order.application.usecase.event.PlaceOrderEvent;
import com.joy.joyapi.order.domain.models.Order;
import com.joy.joyapi.order.domain.models.OrderItem;
import com.joy.joyapi.order.domain.repository.OrderRepository;
import com.joy.joyapi.order.domain.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceOrderService implements PlaceOrderUseCase {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final OrderService orderService;
    private final CouponRepository couponRepository;
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
        List<UUID> sellerIds = itemRepository.findAllByIdIn(itemIds)
                .stream()
                .map(Item::getSellerId)
                .toList();
        if (order.getCouponId() != null) {
            AbstractCoupon coupon = couponRepository.findById(order.getCouponId())
                    .orElseThrow(() -> new EntityNotFoundException("쿠폰 정보를 찾을 수 없습니다."));
            coupon.use();
            couponRepository.save(coupon);
        }

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
        List<Item> items = itemRepository.findAllByIdIn(orderItemMap.keySet());

        AbstractCoupon coupon = null;
        if (request.couponId() != null) {
            coupon = couponRepository.findById(request.couponId())
                    .orElseThrow(IllegalArgumentException::new);
        }
        Order newOrder = orderService.order(request.buyerId(), items, orderItemMap, coupon);
        Order savedOrder = orderRepository.save(newOrder);

        return CreateProvisionalOrderResponse.of(savedOrder);
    }
}
