package com.joy.joypayment.application.dto;

import java.util.UUID;

public record CompletePaymentRequest(
        UUID requestId
) {
}
