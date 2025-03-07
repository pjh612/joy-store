package com.joy.joyapi.order.application.usecase.dto;

import java.util.List;
import java.util.UUID;

public record CreateProvisionalOrderRequest(
        List<OrderItemRequestDto> orderItems,
        UUID couponId,
        UUID buyerId,
        String payType
) {
    public record OrderItemRequestDto(
            UUID itemId,
            Integer quantity
    ) {
    }
}

