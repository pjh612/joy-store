package com.joy.joyapi.order.application.usecase.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ConfirmOrderRequest(
        UUID orderId,
        BigDecimal amount
) {
}
