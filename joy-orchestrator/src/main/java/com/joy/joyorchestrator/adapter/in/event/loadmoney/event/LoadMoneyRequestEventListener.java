package com.joy.joyorchestrator.adapter.in.event.loadmoney.event;

import com.joy.joyorchestrator.adapter.in.event.loadmoney.event.payload.LoadMoneyRequestEventPayload;
import com.joy.joyorchestrator.application.SagaManager;
import com.joy.joyorchestrator.domain.model.PayloadType;
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
public class LoadMoneyRequestEventListener {
    private final SagaManager sagaManager;

    @Transactional
    @KafkaListener(topics = "${kafka.topic.saga.load-money-request.inbox.events}", containerFactory = "kafkaListenerContainerFactory")
    void listen(@Header(KafkaHeaders.RECEIVED_KEY) UUID sagaId,
                @Header("id") String eventId,
                @Payload LoadMoneyRequestEventPayload payloadObject) {
        if (payloadObject.getType() != PayloadType.FAILED) {
            sagaManager.begin(sagaId, "LOAD_MONEY_SAGA", payloadObject);
        }
    }
}
