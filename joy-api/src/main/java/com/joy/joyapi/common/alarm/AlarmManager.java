package com.joy.joyapi.common.alarm;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface AlarmManager {

    void notice(AlarmChannel alarmChannel, String targetId, String message);

    SseEmitter subscribe(AlarmChannel alarmChannel, String subscriberId, String lastEventId);
}
