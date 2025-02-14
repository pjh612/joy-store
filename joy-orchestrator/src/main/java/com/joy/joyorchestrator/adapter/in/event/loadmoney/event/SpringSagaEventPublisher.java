package com.joy.joyorchestrator.adapter.in.event.loadmoney.event;

import com.joy.joyorchestrator.application.SagaEventPublisher;
import com.joy.joyorchestrator.domain.model.SagaEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpringSagaEventPublisher implements SagaEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void publishEvent(SagaEvent sagaEvent) {
        eventPublisher.publishEvent(sagaEvent);
    }
}
