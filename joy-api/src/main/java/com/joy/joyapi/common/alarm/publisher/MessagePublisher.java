package com.joy.joyapi.common.alarm.publisher;

import com.joy.joyapi.common.alarm.MessageDto;

public interface MessagePublisher {
    void publish(String channel, MessageDto messageDto);
}
