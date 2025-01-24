package com.joy.joyapi.order.application.usecase.impl;

import com.joy.joyapi.coupon.domain.model.AbstractCoupon;
import com.joy.joyapi.coupon.domain.repository.CouponRepository;
import com.joy.joyapi.item.domain.models.Item;
import com.joy.joyapi.item.domain.repository.ItemRepository;
import com.joy.joyapi.order.application.usecase.PlaceOrderUseCase;
import com.joy.joyapi.order.application.usecase.dto.PlaceOrderRequest;
import com.joy.joyapi.order.application.usecase.dto.PlaceOrderResponse;
import com.joy.joyapi.order.application.usecase.event.PlaceOrderEvent;
import com.joy.joyapi.order.domain.models.Order;
import com.joy.joyapi.order.domain.repository.OrderRepository;
import com.joy.joyapi.order.domain.service.OrderService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PlaceOrderService implements PlaceOrderUseCase {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final OrderService orderService;
    private final CouponRepository couponRepository;
    private final ApplicationEventPublisher publisher;

    public PlaceOrderService(OrderRepository orderRepository, ItemRepository itemRepository, OrderService orderService, CouponRepository couponRepository, ApplicationEventPublisher publisher) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.orderService = orderService;
        this.couponRepository = couponRepository;
        this.publisher = publisher;
    }

    @Override
    @Transactional
    public PlaceOrderResponse order(PlaceOrderRequest request) {
        Map<Long, Integer> orderItemMap = request.orderItems()
                .stream()
                .collect(Collectors.toMap(PlaceOrderRequest.OrderItemRequestDto::itemSeq, PlaceOrderRequest.OrderItemRequestDto::quantity));
        List<Long> itemSeqeunceList = orderItemMap.keySet()
                .stream()
                .toList();
        List<Item> items = itemRepository.findAllBySequenceIn(itemSeqeunceList);

        AbstractCoupon coupon = null;
        if(request.couponSequence() != null) {
            coupon = couponRepository.findBySequence(request.couponSequence())
                    .orElseThrow(IllegalArgumentException::new);
        }
        Order newOrder = orderService.order(request.buyerSeq(), items, orderItemMap, coupon);
        Order savedOrder = orderRepository.save(newOrder);

        List<Long> sellerSequenceList = items.stream()
                .map(Item::getSellerSeq)
                .toList();
        publisher.publishEvent(new PlaceOrderEvent(sellerSequenceList, savedOrder.getSeq()));

        return new PlaceOrderResponse(savedOrder.getSeq());
    }
}
