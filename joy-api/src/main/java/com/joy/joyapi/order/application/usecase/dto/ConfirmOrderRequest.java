package com.joy.joyapi.order.application.usecase.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record ConfirmOrderRequest(
        @NotNull
        UUID orderId,
        @NotNull
        BigDecimal amount
) {
}
