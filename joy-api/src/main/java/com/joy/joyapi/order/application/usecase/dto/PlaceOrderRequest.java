package com.joy.joyapi.order.application.usecase.dto;

import java.util.List;

public record PlaceOrderRequest(
        List<OrderItemRequestDto> orderItems,
        String couponId,
        String buyerId) {

    public record OrderItemRequestDto(
            String itemId,
            Integer quantity
    ) {
    }
}
