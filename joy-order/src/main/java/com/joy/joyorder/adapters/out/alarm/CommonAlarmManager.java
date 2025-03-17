package com.joy.joyorder.adapters.out.alarm;

import com.joy.joyorder.adapters.out.alarm.cache.AlarmCacheManager;
import com.joy.joyorder.adapters.out.alarm.publisher.MessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.Duration;

@Slf4j
@Service
public class CommonAlarmManager implements AlarmManager {
    private final MessagePublisher messagePublisher;
    private final EmitterRepository emitterRepository;
    private final AlarmCacheManager<MessageDto> alarmCacheManager;

    public CommonAlarmManager(MessagePublisher messagePublisher, EmitterRepository emitterRepository, AlarmCacheManager<MessageDto> alarmCacheManager) {
        this.messagePublisher = messagePublisher;
        this.emitterRepository = emitterRepository;
        this.alarmCacheManager = alarmCacheManager;
    }

    @Override
    public void notice(AlarmChannel alarmChannel, String targetId, String message) {
        messagePublisher.publish(alarmChannel.name(), MessageDto.of(targetId, message));
    }

    @Override
    public SseEmitter subscribe(AlarmChannel alarmChannel, String subscriberId, String lastEventId) {
        SseEmitter emitter = new SseEmitter(Duration.ofSeconds(10).toMillis());
        emitter.onTimeout(() -> {
            emitterRepository.deleteById(subscriberId);
            emitter.complete();
        });
        emitter.onCompletion(() -> emitterRepository.deleteById(subscriberId));
        emitter.onError(e -> {
            log.error("Error occurred on SSE emitter id = {}, message = {}", subscriberId, e.getMessage(), e);
            emitter.complete();
        });
        emitterRepository.put(subscriberId, emitter);
        messagePublisher.publish(alarmChannel.name(), MessageDto.of(subscriberId, "connect", "connected"));
        if (StringUtils.hasText(lastEventId)) {
            alarmCacheManager.getFromOffset("alarm:" + subscriberId, Long.parseLong(lastEventId), MessageDto.class)
                    .forEach(it -> messagePublisher.publish(alarmChannel.name(), it));
        }

        return emitter;
    }
}
