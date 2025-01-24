package com.joy.joyapi.order.application.usecase.impl;

import com.joy.joyapi.common.alarm.AlarmChannel;
import com.joy.joyapi.common.alarm.AlarmManager;
import com.joy.joyapi.order.application.usecase.SubscribePlaceOrderAlarmUseCase;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class SubscribePlaceOrderAlarmService implements SubscribePlaceOrderAlarmUseCase {
    private final AlarmManager alarmManager;

    public SubscribePlaceOrderAlarmService(AlarmManager alarmManager) {
        this.alarmManager = alarmManager;
    }

    @Override
    public SseEmitter subscribe(String subscriberId, String lastEventId) {
        return alarmManager.subscribe(AlarmChannel.ADMIN_ORDER, subscriberId, lastEventId);
    }
}
