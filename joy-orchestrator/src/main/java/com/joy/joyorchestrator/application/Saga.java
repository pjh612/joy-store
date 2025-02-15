package com.joy.joyorchestrator.application;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.joy.joyorchestrator.domain.model.*;
import com.joy.joyorchestrator.domain.repository.EventLogRepository;
import com.joy.joyorchestrator.domain.repository.SagaStateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class Saga {
    private final SagaEventPublisher eventPublisher;
    private final ObjectMapper objectMapper;
    private final EventLogRepository eventLogRepository;
    private final SagaStateRepository sagaStateRepository;
    private final SagaState state;
    private final SagaStepFlow sagaStepFlow;


    public void init(Object payload) {
        advance(payload);
        sagaStateRepository.save(state);
    }

    public void handle(UUID eventId, SagaPayload eventPayload) {
        Object nextEventPayload = eventPayload.toEvent();

        ensureProcessed(eventId, () -> {
                    if (eventPayload.type().isSucceeded()) {
                        onStepEvent(SagaStepStatus.SUCCEEDED, nextEventPayload);
                    } else {
                        onStepEvent(SagaStepStatus.FAILED, nextEventPayload);
                    }
                }
        );
    }

    private void ensureProcessed(UUID eventId, Runnable runnable) {
        if (eventLogRepository.findById(eventId) != null) {
            return;
        }

        runnable.run();

        eventLogRepository.save(EventLog.createNew(eventId));
    }

    private void advance(Object payload) {
        String currentStepName = state.getCurrentStep();
        SagaStep next;
        if (currentStepName == null) {
            next = sagaStepFlow.getFirst();
        } else {
            next = sagaStepFlow.getStep(currentStepName).next();
        }
        if (next.topic() == null) {
            state.updateCurrentStep(null);
            return;
        }

        eventPublisher.publishEvent(new SagaEvent(state.getId(), next.topic(), PayloadType.REQUEST.name(), objectMapper.convertValue(payload, JsonNode.class)));

        state.updateStepStatus(next.topic(), SagaStepStatus.STARTED);
        state.updateCurrentStep(next.topic());
    }

    private void goBack() {
        SagaStep step = sagaStepFlow.getStep(state.getCurrentStep());
        var prev = step.prev();
        if (prev.topic() == null) {
            state.updateCurrentStep(null);

            return;
        }

        var payload = ((ObjectNode) state.getPayload().deepCopy());
        payload.put("type", PayloadType.CANCEL.name());

        eventPublisher.publishEvent(new SagaEvent(state.getId(), prev.topic(), PayloadType.CANCEL.name(), payload));

        state.updateStepStatus(prev.topic(), SagaStepStatus.COMPENSATING);
        state.updateCurrentStep(prev.topic());
    }

    private void onStepEvent(SagaStepStatus status, Object payload) {
        state.updateStepStatus(state.getCurrentStep(), status);

        if (status.isSucceeded()) {
            advance(payload);
        } else if (status.isFailedOrCompensated()) {
            goBack();
        }

        state.advanceSagaStatus();
        sagaStateRepository.save(state);
    }


}
