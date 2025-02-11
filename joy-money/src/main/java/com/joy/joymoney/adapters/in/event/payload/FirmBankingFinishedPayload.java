package com.joy.joymoney.adapters.in.event.payload;

import lombok.*;

import java.math.BigDecimal;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FirmBankingFinishedPayload {
    private String loadMoneyRequestId;
    private String memberId;
    private BigDecimal amount;
    private String type;
}
