package com.joy.joyorchestrator.application;

import com.joy.joyorchestrator.domain.model.PayloadType;

public interface SagaPayload {
    PayloadType type();

    Object toEvent();
}
