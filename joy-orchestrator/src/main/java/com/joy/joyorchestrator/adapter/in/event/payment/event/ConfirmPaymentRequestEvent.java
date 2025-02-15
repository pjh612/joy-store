package com.joy.joyorchestrator.adapter.in.event.payment.event;

import java.math.BigDecimal;
import java.util.UUID;

public record ConfirmPaymentRequestEvent(
        UUID requestId,
        UUID requestMemberId,
        BigDecimal requestPrice,
        BigDecimal balance
) {
}
