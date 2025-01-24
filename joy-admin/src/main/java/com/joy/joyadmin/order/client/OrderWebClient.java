package com.joy.joyadmin.order.client;

import com.joy.joyadmin.order.dto.FindOrderResponse;
import com.joy.joycommon.api.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderWebClient implements OrderClient {
    private final WebClient webClient;

    @Override
    public ApiResponse<List<FindOrderResponse>> getAllBySellerSequence(Long sequence) {
        return webClient.get()
                .uri("/api/sellers/{sellerSequence}/orders", sequence)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<List<FindOrderResponse>>>() {
                })
                .block();
    }

    @Override
    public Flux<ServerSentEvent<String>> subscribeAlarm(Long memberSequence, String lastEventId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/orders/alarm/subscription")
                        .queryParam("memberSequence", memberSequence)
                        .build())
                .header("contentType", "text/event-stream")
                .header("Last-Event-Id", lastEventId)
                .retrieve()
                .bodyToFlux(new ParameterizedTypeReference<ServerSentEvent<String>>() {
                });
    }
}
