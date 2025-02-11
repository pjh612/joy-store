package com.joy.joyorchestrator.application;

import com.joy.joyorchestrator.domain.model.SagaEvent;

public interface SagaEventPublisher {

    void publishEvent(SagaEvent sagaEvent);
}
