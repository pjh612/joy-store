package com.joy.joyorchestrator.adapter.in.event;

import com.joy.joycommon.util.UuidUtil;
import com.joy.joyorchestrator.adapter.in.event.payload.LoadMoneyRequestCreatedEventPayload;
import com.joy.joyorchestrator.application.SagaManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoadMoneyRequestCreatedEventListener {
    private final SagaManager sagaManager;

    @Transactional
    @KafkaListener(topics = "${kafka.topic.saga.load-money.inbox.events}", containerFactory = "kafkaListenerContainerFactory")
    void listen(@Header(KafkaHeaders.RECEIVED_KEY) UUID sagaId,
                @Header("id") String eventId,
                @Header("eventType") String eventType,
                @Payload LoadMoneyRequestCreatedEventPayload payload) {
        log.info("Kafka message with key = {}, eventType {} arrived, {}", sagaId, eventType, payload);

        sagaManager.handle(sagaId, UuidUtil.uuidFromBase64(eventId), payload);
    }
}
