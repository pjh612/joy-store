package com.joy.joymoney.adapters.in.event.payload;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoadMoneyRequestedEventPayload {
    private UUID loadMoneyRequestId;
    private UUID memberId;
    private BigDecimal amount;
    private String type;
}
