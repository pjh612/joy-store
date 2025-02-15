package com.joy.joypayment.adapter.in.event.payload;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreatePaymentRequestEventPayload {
    private UUID requestId;
    private UUID requestMemberId;
    private BigDecimal requestPrice;
    private UUID sellerId;
    private String type;
}
