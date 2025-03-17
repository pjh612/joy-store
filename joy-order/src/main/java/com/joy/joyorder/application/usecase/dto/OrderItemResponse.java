package com.joy.joyorder.application.usecase.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemResponse(
        UUID id,
        UUID itemId,
        BigDecimal unitPrice,
        Integer quantity,
        BigDecimal discountAmount
) {
}
