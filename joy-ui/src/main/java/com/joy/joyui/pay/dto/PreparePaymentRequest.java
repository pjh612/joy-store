package com.joy.joyui.pay.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record PreparePaymentRequest(
        UUID orderId,
        BigDecimal requestPrice,
        String productName
) {
}
