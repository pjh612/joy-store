package com.joy.joyorder.adapters.out.alarm;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface AlarmManager {

    void notice(AlarmChannel alarmChannel, String targetId, String message);

    SseEmitter subscribe(AlarmChannel alarmChannel, String subscriberId, String lastEventId);
}
