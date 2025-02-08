package com.joy.joyorchestrator.domain.model;

import com.joy.joyorchestrator.domain.repository.EventLogRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public abstract class Saga {

    private final EventLogRepository eventLogRepository;

    protected void ensureProcessed(UUID eventId, Runnable callback) {
        if (eventLogRepository.findById(eventId) != null) {
            return;
        }

        callback.run();

        eventLogRepository.save(EventLog.createNew(eventId));
    }

    public enum PayloadType {
        REQUEST, CANCEL
    }
}
