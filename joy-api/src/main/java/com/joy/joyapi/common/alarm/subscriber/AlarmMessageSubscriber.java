package com.joy.joyapi.common.alarm.subscriber;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joy.joyapi.common.alarm.EmitterRepository;
import com.joy.joyapi.common.alarm.MessageDto;
import com.joy.joyapi.common.alarm.cache.AlarmCacheManager;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Objects;

@Component
public class AlarmMessageSubscriber implements MessageListener {
    private final EmitterRepository emitterRepository;
    private final AlarmCacheManager<MessageDto> alarmCacheManager;
    private final ObjectMapper objectMapper;

    private static final String MESSAGE_TYPE = "message";
    private static final String ALARM_CACHE_KEY_PREFIX = "alarm:";

    public AlarmMessageSubscriber(EmitterRepository emitterRepository, AlarmCacheManager<MessageDto> alarmCacheManager, ObjectMapper objectMapper) {
        this.emitterRepository = emitterRepository;
        this.alarmCacheManager = alarmCacheManager;
        this.objectMapper = objectMapper;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            MessageDto alarmMessage = objectMapper.readValue(message.getBody(), MessageDto.class);
            emitterRepository.getById(alarmMessage.targetId())
                    .ifPresent(it -> sendEvent(it, alarmMessage));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendEvent(SseEmitter emitter, MessageDto messageDto) {
        long id = System.currentTimeMillis();
        SseEmitter.SseEventBuilder eventBuilder = SseEmitter.event()
                .name(messageDto.type())
                .data(messageDto.body())
                .id(Long.toString(id));

        if (Objects.equals(messageDto.type(), MESSAGE_TYPE)) {
            alarmCacheManager.save(ALARM_CACHE_KEY_PREFIX + messageDto.targetId(), Long.toString(id), messageDto);
        }

        try {
            emitter.send(eventBuilder.build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
