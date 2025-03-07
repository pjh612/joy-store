package com.joy.joyui.pay.client;

import com.joy.joyui.pay.client.dto.PreparePaymentRequest;
import com.joy.joyui.pay.dto.ConfirmPaymentRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Component
public class PaymentWebClient implements PaymentClient {
    private final WebClient paymentClient;

    public PaymentWebClient(WebClient paymentClient) {
        this.paymentClient = paymentClient;
    }

    @Override
    public PreparePaymentResponse preparePayment(PreparePaymentRequest request) {
        return paymentClient.post()
                .uri("/api/payment/prepare")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(PreparePaymentResponse.class)
                .block();
    }

    @Override
    public void confirmPayment(ConfirmPaymentRequest request) {
        paymentClient.post()
                .uri("/api/payment/confirm")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    @Override
    public Flux<ServerSentEvent<String>> subscribePaymentResult(UUID paymentId, String lastEventId) {
        return paymentClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/payment-result/subscribe")
                        .queryParam("paymentId", paymentId)
                        .build())
                .header("contentType", "text/event-stream")
                .header("Last-Event-Id", lastEventId)
                .retrieve()
                .bodyToFlux(new ParameterizedTypeReference<>() {
                });
    }
}
