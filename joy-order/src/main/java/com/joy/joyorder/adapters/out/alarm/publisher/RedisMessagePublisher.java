package com.joy.joyorder.adapters.out.alarm.publisher;

import com.joy.joyorder.adapters.out.alarm.MessageDto;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisMessagePublisher implements MessagePublisher {
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisMessagePublisher(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void publish(String channel, MessageDto messageDto) {
        redisTemplate.convertAndSend(channel, messageDto);
    }
}
