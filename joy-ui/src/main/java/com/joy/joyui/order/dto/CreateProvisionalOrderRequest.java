package com.joy.joyui.order.dto;

import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateProvisionalOrderRequest(
        @Size(min = 1)
        List<OrderItemRequestDto> orderItems,
        String couponId,
        String payType
) {
    public record OrderItemRequestDto(
            String itemId,
            Integer quantity
    ) {
    }
}

