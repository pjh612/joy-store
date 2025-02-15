package com.joy.joyorchestrator.adapter.in.event.payment.event.payload;

import com.joy.joyorchestrator.adapter.in.event.payment.event.ConfirmPaymentRequestEvent;
import com.joy.joyorchestrator.application.SagaPayload;
import com.joy.joyorchestrator.domain.model.PayloadType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DecreasedMoneyEventPayload implements SagaPayload {
    private UUID requestId;
    private UUID moneyChangingRequestId;
    private UUID requestMemberId;
    private BigDecimal requestPrice;
    private BigDecimal balance;
    private PayloadType type;

    @Override
    public PayloadType type() {
        return this.type;
    }

    @Override
    public Object toEvent() {
        return new ConfirmPaymentRequestEvent(this.requestId, this.requestMemberId, this.requestPrice, this.balance);
    }
}
