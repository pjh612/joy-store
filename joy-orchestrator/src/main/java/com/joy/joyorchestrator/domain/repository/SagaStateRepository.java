package com.joy.joyorchestrator.domain.repository;

import com.joy.joyorchestrator.domain.model.SagaState;

import java.util.UUID;

public interface SagaStateRepository {
    SagaState save(SagaState sagaState);

    SagaState findById(UUID sagaId);
}
