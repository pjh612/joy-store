package com.joy.joyorchestrator.adapter.in.event.payload;

import com.joy.joyorchestrator.domain.model.PayloadType;
import com.joy.joyorchestrator.application.SagaPayload;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoadMoneyFinishedEventPayload implements SagaPayload {
    private String status;
    private String loadMoneyRequestId;
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
