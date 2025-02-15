package com.joy.joyorchestrator.adapter.in.event.payment.event.payload;

import com.joy.joyorchestrator.domain.model.PayloadType;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentRequestEventPayload(
        UUID requestId,
        UUID requestMemberId,
        BigDecimal requestPrice,
        UUID sellerId,
        PayloadType type) {
}
