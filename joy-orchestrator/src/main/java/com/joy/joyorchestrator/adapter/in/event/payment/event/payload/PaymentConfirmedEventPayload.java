package com.joy.joyorchestrator.adapter.in.event.payment.event.payload;

import com.joy.joyorchestrator.application.SagaPayload;
import com.joy.joyorchestrator.domain.model.PayloadType;
import lombok.*;

import java.util.UUID;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentConfirmedEventPayload implements SagaPayload {
    private UUID sagaId;
    private UUID requestId;
    private PayloadType type;

    @Override
    public PayloadType type() {
        return this.type;
    }

    @Override
    public Object toEvent() {
        return null;
    }
}
