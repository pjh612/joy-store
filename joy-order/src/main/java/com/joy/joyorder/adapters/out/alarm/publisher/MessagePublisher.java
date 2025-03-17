package com.joy.joyorder.adapters.out.alarm.publisher;


import com.joy.joyorder.adapters.out.alarm.MessageDto;

public interface MessagePublisher {
    void publish(String channel, MessageDto messageDto);
}
