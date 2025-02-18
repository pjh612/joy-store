package com.joy.joypayment.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record PreparePaymentRequest(
        UUID requestMemberId,
        UUID clientId,
        BigDecimal requestPrice
) {
}
