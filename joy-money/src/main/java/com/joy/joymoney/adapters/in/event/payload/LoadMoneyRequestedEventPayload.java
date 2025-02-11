package com.joy.joymoney.adapters.in.event.payload;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoadMoneyRequestedEventPayload {
    private String loadMoneyRequestId;
    private String memberId;
    private BigDecimal amount;
    private String type;
}
