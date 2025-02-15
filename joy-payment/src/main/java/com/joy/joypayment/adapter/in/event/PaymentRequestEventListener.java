package com.joy.joypayment.adapter.in.event;

import com.joy.joypayment.adapter.in.event.payload.CreatePaymentRequestEventPayload;
import com.joy.joypayment.adapter.in.event.handler.PaymentRequestEventHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentRequestEventListener {
    private final PaymentRequestEventHandler handler;

    @KafkaListener(
            topics = "${kafka.topic.payment-request.inbox.events.name}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void handle(
            @Header(KafkaHeaders.RECEIVED_KEY) UUID sagaId,
            @Header("id") String eventId,
            @Header("eventType") String eventType,
            @Payload CreatePaymentRequestEventPayload payload) {
        log.info("paymentRequest event received");
        handler.handle(sagaId, payload);
    }

}
