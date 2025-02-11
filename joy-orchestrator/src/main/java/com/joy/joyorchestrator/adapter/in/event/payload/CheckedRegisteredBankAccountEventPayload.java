package com.joy.joyorchestrator.adapter.in.event.payload;

import com.joy.joyorchestrator.adapter.in.event.RequestFirmBankingEvent;
import com.joy.joyorchestrator.domain.model.PayloadType;
import com.joy.joyorchestrator.application.SagaPayload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckedRegisteredBankAccountEventPayload implements SagaPayload {
    private String loadMoneyRequestId;
    private String checkRegisteredBankAccountId;
    private String moneyId;
    private String memberId;
    private PayloadType type;
    private BigDecimal amount;
    private String fromBankName;
    private String fromBankAccountNumber;

    @Override
    public PayloadType type() {
        return type;
    }

    @Override
    public RequestFirmBankingEvent toEvent() {
        return new RequestFirmBankingEvent(
                this.loadMoneyRequestId,
                this.memberId,
                this.fromBankName,
                this.fromBankAccountNumber,
                "joy",
                "joy",
                this.amount);
    }
}
