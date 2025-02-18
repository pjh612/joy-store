package com.joy.joypayment.application.dto;

import com.joy.joypayment.domain.model.PaymentRequest;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record PaymentResponse(UUID requestId,
                              UUID requestMemberId,
                              BigDecimal requestPrice,
                              int paymentStatus,
                              Instant approvedAt) {

    public static PaymentResponse of(PaymentRequest paymentRequest) {
        return new PaymentResponse(
                paymentRequest.getRequestId(),
                paymentRequest.getRequestMemberId(),
                paymentRequest.getRequestPrice(),
                paymentRequest.getPaymentStatus(),
                paymentRequest.getApprovedAt()
        );
    }
}
