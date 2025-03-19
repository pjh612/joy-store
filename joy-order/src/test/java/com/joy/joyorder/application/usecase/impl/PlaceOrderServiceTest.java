package com.joy.joyorder.application.usecase.impl;

import com.fasterxml.uuid.Generators;
import com.joy.joyorder.application.client.CouponClient;
import com.joy.joyorder.application.client.ItemClient;
import com.joy.joyorder.application.client.dto.CouponResponse;
import com.joy.joyorder.application.client.dto.ItemResponse;
import com.joy.joyorder.application.usecase.dto.ConfirmOrderRequest;
import com.joy.joyorder.application.usecase.dto.CreateProvisionalOrderRequest;
import com.joy.joyorder.application.usecase.dto.CreateProvisionalOrderResponse;
import com.joy.joyorder.application.usecase.dto.OrderItemResponse;
import com.joy.joyorder.application.usecase.event.PlaceOrderEvent;
import com.joy.joyorder.domain.models.Coupon;
import com.joy.joyorder.domain.models.Order;
import com.joy.joyorder.domain.models.OrderStatus;
import com.joy.joyorder.domain.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlaceOrderServiceTest {
    @InjectMocks
    private PlaceOrderService placeOrderService;

    @Mock
    private ItemClient itemClient;
    @Mock
    private ApplicationEventPublisher eventPublisher;
    @Mock
    private CouponClient couponClient;
    @Mock
    private OrderRepository orderRepository;


    @Test
    void confirmOrder_ShouldCompleteOrderAndPublishEvent() {
        //given
        UUID orderId = UUID.randomUUID();
        Order order = Order.createNew(UUID.randomUUID());
        ItemResponse itemResponse = new ItemResponse(UUID.randomUUID(), BigDecimal.valueOf(1000), "itemName", "itemDescription", UUID.randomUUID());
        order.addOrderItem(UUID.randomUUID(), itemResponse.title(), itemResponse.sellerId(), UUID.randomUUID(), itemResponse.price(), 2, BigDecimal.valueOf(500));

        given(orderRepository.findById(orderId)).willReturn(Optional.of(order));
        given(itemClient.findAllByIdIn(anyList())).willReturn(List.of(itemResponse));
        doNothing().when(couponClient).useCoupons(anyList());
        doNothing().when(eventPublisher).publishEvent(any(PlaceOrderEvent.class));

        ConfirmOrderRequest request = new ConfirmOrderRequest(orderId, BigDecimal.valueOf(1500));

        //when
        placeOrderService.confirmOrder(request);

        //then
        verify(couponClient).useCoupons(anyList());
        verify(orderRepository).save(order);
        verify(eventPublisher).publishEvent(any(PlaceOrderEvent.class));

        Assertions.assertThat(order.getStatus()).isEqualTo(OrderStatus.ORDER_COMPLETED);
        Assertions.assertThat(order.getOrderItems().size()).isEqualTo(1);

    }

    @Test
    void confirmOrder_ShouldThrowException_WhenTotalPayPriceIsDifferentWithRequestAmount() {
        UUID orderId = UUID.randomUUID();
        Order order = Order.createNew(UUID.randomUUID());
        ItemResponse itemResponse = new ItemResponse(UUID.randomUUID(), BigDecimal.valueOf(1000), "itemName", "itemDescription", UUID.randomUUID());
        order.addOrderItem(UUID.randomUUID(), itemResponse.title(), itemResponse.sellerId(), UUID.randomUUID(), itemResponse.price(), 2, BigDecimal.valueOf(500));

        given(orderRepository.findById(orderId)).willReturn(Optional.of(order));

        ConfirmOrderRequest request = new ConfirmOrderRequest(orderId, BigDecimal.valueOf(2000));

        //when, then
        Assertions.assertThatThrownBy(() -> placeOrderService.confirmOrder(request)).isInstanceOf(IllegalArgumentException.class).hasMessage("결제 금액이 주문 금액과 일치하지 않습니다.");
    }

    @Test
    void confirmOrder_ShouldThrowException_WhenOrderNotFound() {
        //given
        UUID orderId = UUID.randomUUID();
        given(orderRepository.findById(orderId)).willReturn(Optional.empty());

        //when
        ConfirmOrderRequest request = new ConfirmOrderRequest(orderId, BigDecimal.TEN);

        //then
        Assertions.assertThatThrownBy(() -> placeOrderService.confirmOrder(request));
    }

    @Test
    void createProvisionalOrder_ShouldCreateAndReturnProvisionalOrder() {
        UUID buyerId = Generators.timeBasedEpochGenerator().generate();
        UUID itemId = Generators.timeBasedEpochGenerator().generate();
        UUID couponId = Generators.timeBasedEpochGenerator().generate();
        UUID sellerId = Generators.timeBasedEpochGenerator().generate();
        ItemResponse itemResponse = new ItemResponse(itemId, BigDecimal.valueOf(1000), "상품1", "상품설명", sellerId);
        CouponResponse couponResponse = new CouponResponse(couponId, sellerId, "첫 주문 쿠폰", "AVAILABLE", "FIXED", 1000);
        Coupon coupon = new Coupon(couponResponse.id(), Coupon.CouponStatus.valueOf(couponResponse.status()), Coupon.CouponType.valueOf(couponResponse.type()), couponResponse.value());
        int quantity = 2;


        CreateProvisionalOrderRequest.OrderItemRequestDto orderItemRequestDto =
                new CreateProvisionalOrderRequest.OrderItemRequestDto(itemId, couponId, quantity);
        CreateProvisionalOrderRequest request = new CreateProvisionalOrderRequest(List.of(orderItemRequestDto), buyerId, "HARU_PAY");

        BigDecimal amount = itemResponse.price().multiply(new BigDecimal(quantity)).subtract(BigDecimal.valueOf(couponResponse.value()));
        BigDecimal discountAmount = coupon.getDiscountAmount(amount);

        Order mockOrder = mock(Order.class);

        Order order = Order.createNew(buyerId);
        order.addOrderItem(itemId, itemResponse.title(), sellerId, couponId, itemResponse.price(), quantity, discountAmount);


        given(itemClient.findAllByIdIn(any())).willReturn(List.of(itemResponse));
        given(couponClient.findByCouponIdIn(any())).willReturn(List.of(couponResponse));
        given(orderRepository.save(any(Order.class))).willReturn(order);

        CreateProvisionalOrderResponse expectResponse = new CreateProvisionalOrderResponse(mockOrder.getId(), buyerId, OrderStatus.PAYMENT_WAITING, List.of(new OrderItemResponse(null, itemId, itemResponse.price(), orderItemRequestDto.quantity(), BigDecimal.valueOf(couponResponse.value()))), discountAmount);

        //when
        CreateProvisionalOrderResponse response = placeOrderService.createProvisionalOrder(request);

        //then
        Assertions.assertThat(response.amount()).isEqualTo(expectResponse.amount());
        Assertions.assertThat(response.buyerId()).isEqualTo(expectResponse.buyerId());
        Assertions.assertThat(response.orderItems().size()).isEqualTo(expectResponse.orderItems().size());
        Assertions.assertThat(response.orderItems()).isEqualTo(expectResponse.orderItems());

        verify(itemClient).findAllByIdIn(any());
        verify(couponClient).findByCouponIdIn(any());
        verify(orderRepository).save(any(Order.class));
    }
}


