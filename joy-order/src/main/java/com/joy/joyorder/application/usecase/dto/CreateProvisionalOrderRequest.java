package com.joy.joyorder.application.usecase.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record CreateProvisionalOrderRequest(
        @Size(min = 1)
        List<OrderItemRequestDto> orderItems,
        UUID buyerId,
        String payType
) {
    public record OrderItemRequestDto(
            @NotNull
            UUID itemId,
            UUID couponId,
            @Size(min = 1)
            Integer quantity
    ) {
    }
}

