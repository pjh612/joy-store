package com.joy.joyorchestrator.domain.repository;

import com.joy.joyorchestrator.domain.model.EventLog;

import java.util.UUID;

public interface EventLogRepository {
    EventLog findById(UUID eventId);

    EventLog save(EventLog eventLog);
}
