package com.joy.joypayment.application.dto;

import com.joy.joypayment.domain.model.PaymentRequest;

import java.time.Instant;
import java.util.UUID;

public record PaymentResponse(UUID requestId,
                              UUID requestMemberId,
                              int paymentStatus,
                              Instant approvedAt) {

    public static PaymentResponse of(PaymentRequest paymentRequest) {
        return new PaymentResponse(
                paymentRequest.getRequestId(),
                paymentRequest.getRequestMemberId(),
                paymentRequest.getPaymentStatus(),
                paymentRequest.getApprovedAt()
        );
    }
}
