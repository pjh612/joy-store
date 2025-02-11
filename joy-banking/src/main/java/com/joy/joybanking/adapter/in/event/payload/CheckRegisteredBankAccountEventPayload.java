package com.joy.joybanking.adapter.in.event.payload;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CheckRegisteredBankAccountEventPayload {
    private String loadMoneyRequestId;
    private String moneyId;
    private String memberId;
    private BigDecimal amount;
    private String type;
}
