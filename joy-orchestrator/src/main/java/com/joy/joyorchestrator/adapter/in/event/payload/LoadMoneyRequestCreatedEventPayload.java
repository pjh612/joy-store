package com.joy.joyorchestrator.adapter.in.event.payload;

import com.joy.joyorchestrator.adapter.in.event.CheckRegisteredBankAccountEvent;
import com.joy.joyorchestrator.domain.model.PayloadType;
import com.joy.joyorchestrator.application.SagaPayload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadMoneyRequestCreatedEventPayload implements SagaPayload {
    private UUID loadMoneyRequestId;
    private UUID moneyId;
    private String memberId;
    private BigDecimal amount;
    private PayloadType type;

    @Override
    public PayloadType type() {
        return this.type;
    }

    @Override
    public CheckRegisteredBankAccountEvent toEvent() {
        return new CheckRegisteredBankAccountEvent(
                this.loadMoneyRequestId,
                this.memberId,
                this.moneyId,
                this.amount);
    }
}
