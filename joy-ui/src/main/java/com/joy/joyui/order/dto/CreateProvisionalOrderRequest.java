package com.joy.joyui.order.dto;

import java.util.List;

public record CreateProvisionalOrderRequest(
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

