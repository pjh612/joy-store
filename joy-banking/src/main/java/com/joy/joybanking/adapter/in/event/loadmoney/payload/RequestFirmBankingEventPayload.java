package com.joy.joybanking.adapter.in.event.loadmoney.payload;

import lombok.*;

import java.math.BigDecimal;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestFirmBankingEventPayload {
    private String loadMoneyRequestId;
    private String memberId;
    private String fromBankName;
    private String fromBankAccountNumber;
    private String toBankName;
    private String toBankAccountNumber;
    private BigDecimal amount;
    private String type;
}
