package com.joy.joyorchestrator.domain.model;

public enum SagaStatus {
    STARTED, ABORTING, ABORTED, COMPLETED;

    public boolean isCompleted() {
        return this == COMPLETED;
    }

    public boolean isAborted() {
        return this == ABORTED;
    }
}
