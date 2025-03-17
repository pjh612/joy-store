package com.joy.joyorder.adapters.out.alarm.publisher;

import com.joy.joyorder.adapters.out.alarm.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaMessagePublisher implements MessagePublisher {
    private final KafkaTemplate<String, MessageDto> kafkaTemplate;

    @Override
    public void publish(String channel, MessageDto messageDto) {
        log.info("channel:{}, messageDto:{}", channel, messageDto);
        kafkaTemplate.send(channel, messageDto);
    }
}
