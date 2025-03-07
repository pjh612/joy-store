package com.joy.joyui.pay.client.dto;

import java.math.BigDecimal;

public record PreparePaymentRequest(
        String orderId,
        BigDecimal requestPrice,
        String productName
) {
}
