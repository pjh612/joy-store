package com.joy.joyorchestrator.application;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joy.joyorchestrator.domain.model.SagaState;
import com.joy.joyorchestrator.domain.repository.EventLogRepository;
import com.joy.joyorchestrator.domain.repository.SagaStateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SagaFactory {
    private final EventLogRepository eventLogRepository;
    private final SagaEventPublisher eventPublisher;
    private final SagaStateRepository sagaStateRepository;
    private final ObjectMapper objectMapper;
    private final SagaStepFlowRegistry sagaStepFlowRegistry;

    public Saga createInstance(UUID sagaId) {
        return createInstance(sagaId, null, null);
    }

    public Saga createInstance(UUID sagaId, String sagaType, JsonNode payload) {
        SagaState state = findOrCreateSagaState(sagaId, sagaType, payload);

        return new Saga(eventPublisher, objectMapper, eventLogRepository, sagaStateRepository, state, sagaStepFlowRegistry.get(sagaType));
    }

    private SagaState findOrCreateSagaState(UUID sagaId, String sagaType, JsonNode payload) {
        return sagaStateRepository.findById(sagaId)
                .orElseGet(() -> sagaStateRepository.save(new SagaState(sagaId, sagaType, payload)));
    }

}
