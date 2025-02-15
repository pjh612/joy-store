package com.joy.joypayment.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentRequest {
    private UUID requestId;
    private UUID requestMemberId;
    private BigDecimal requestPrice;
    private UUID sellerId;
    private int paymentStatus;
    private Instant approvedAt;

    public static PaymentRequest createNew(UUID requestId, UUID requestMemberId, BigDecimal requestPrice, UUID sellerId) {
        return new PaymentRequest(requestId, requestMemberId, requestPrice, sellerId, 0, null);
    }

    public void success() {
        this.paymentStatus = 1;
        this.approvedAt = Instant.now();
    }

    public void fail() {
        this.paymentStatus = -1;
        this.approvedAt = null;
    }
}
