package com.joy.joyui.pay.dto;

import java.util.UUID;

public record ConfirmPaymentRequest(
        UUID paymentId
) {
}
