package com.joy.joyapi.order.application.usecase.event;

import com.joy.joyapi.common.alarm.AlarmChannel;
import com.joy.joyapi.common.alarm.AlarmManager;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlaceOrderEventListener {
    private final AlarmManager alarmManager;

    public PlaceOrderEventListener(AlarmManager alarmManager) {
        this.alarmManager = alarmManager;
    }

    @EventListener
    public void event(PlaceOrderEvent event) {
        List<String> noticeTargetIdList = event.getSellerSequenceList()
                .stream()
                .map(Object::toString)
                .toList();
        Long orderSequence = event.getOrderSequence();
        String message = "주문이 들어왔습니다. (주문번호 : " + orderSequence + ")";

        for (String noticeTargetId : noticeTargetIdList) {
            alarmManager.notice(AlarmChannel.ADMIN_ORDER, noticeTargetId, message);
        }
    }
}
