package com.joy.joyorchestrator.adapter.in.event;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CheckRegisteredBankAccountEvent {
    private UUID loadMoneyRequestId;
    private String memberId;
    private UUID moneyId;
    private BigDecimal amount;

}
