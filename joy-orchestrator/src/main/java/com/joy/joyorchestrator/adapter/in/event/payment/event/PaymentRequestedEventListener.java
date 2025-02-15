package com.joy.joyorchestrator.adapter.in.event.payment.event;

import com.joy.joycommon.util.UuidUtil;
import com.joy.joyorchestrator.adapter.in.event.payment.event.payload.PaymentRequestCreatedEventPayload;
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
public class PaymentRequestedEventListener {
    private final SagaManager sagaManager;

    @Transactional
    @KafkaListener(topics = "${kafka.topic.saga.payment-request-created.inbox.events}", containerFactory = "kafkaListenerContainerFactory")
    void listen(@Header(KafkaHeaders.RECEIVED_KEY) UUID sagaId,
                @Header("id") String eventId,
                @Payload PaymentRequestCreatedEventPayload payload) {
        log.info("PaymentRequestCreatedEventPayload = {}", payload);
        sagaManager.handle(sagaId, UuidUtil.uuidFromBase64(eventId), payload);

    }
}
