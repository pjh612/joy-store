package com.joy.joyapi.order.application.usecase.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record CreateProvisionalOrderRequest(
        @Size(min = 1)
        List<OrderItemRequestDto> orderItems,
        UUID couponId,
        UUID buyerId,
        String payType
) {
    public record OrderItemRequestDto(
            @NotNull
            UUID itemId,
            @Size(min = 1)
            Integer quantity
    ) {
    }
}

