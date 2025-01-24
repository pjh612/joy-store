package com.joy.joyapi.common.alarm;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Optional;

public interface EmitterRepository {
    void put(String id, SseEmitter emitter);

    Optional<SseEmitter> getById(String id);

    void deleteById(String id);
}
