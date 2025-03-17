package com.joy.joyorder.application.usecase;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SubscribePlaceOrderAlarmUseCase {
    SseEmitter subscribe(String subscriberId, String lastEventId);
}
