package com.joy.joypayment.adapter.in.event.handler;

import com.joy.joypayment.adapter.in.event.PaymentConfirmedEvent;
import com.joy.joypayment.adapter.in.event.payload.ConfirmPaymentRequestEventPayload;
import com.joy.joypayment.application.dto.CompletePaymentRequest;
import com.joy.joypayment.application.usecase.PaymentConfirmUseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConfirmPaymentRequestEventHandler {
    private final ApplicationEventPublisher eventPublisher;
    private final PaymentConfirmUseCase paymentConfirmUseCase;

    @Transactional
    public void handle(UUID sagaId, ConfirmPaymentRequestEventPayload payload) {
        if ("CANCEL".equals(payload.getType())) {
            paymentConfirmUseCase.failConfirm(payload.getRequestId());
            eventPublisher.publishEvent(PaymentConfirmedEvent.fail(sagaId, payload.getRequestId()));
        } else {
            try {
                paymentConfirmUseCase.confirm(new CompletePaymentRequest(payload.getRequestId()));
                eventPublisher.publishEvent(PaymentConfirmedEvent.success(sagaId, payload.getRequestId()));
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                eventPublisher.publishEvent(PaymentConfirmedEvent.fail(sagaId, payload.getRequestId()));
            }
        }
    }
}
