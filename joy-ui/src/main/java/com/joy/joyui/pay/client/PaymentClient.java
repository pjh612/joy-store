package com.joy.joyui.pay.client;

import com.joy.joyui.pay.client.dto.PreparePaymentRequest;
import com.joy.joyui.pay.dto.ConfirmPaymentRequest;
import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface PaymentClient {
    PreparePaymentResponse preparePayment(PreparePaymentRequest request);

    void confirmPayment(ConfirmPaymentRequest request);

    Flux<ServerSentEvent<String>> subscribePaymentResult(UUID paymentId, String lastEventId);
}
