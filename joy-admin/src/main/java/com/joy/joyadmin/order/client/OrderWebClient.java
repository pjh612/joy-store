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
    public ApiResponse<List<FindOrderResponse>> getAllBySellerSequence(String id) {
        return webClient.get()
                .uri("/api/sellers/{sellerId}/orders", id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<List<FindOrderResponse>>>() {
                })
                .block();
    }

    @Override
    public Flux<ServerSentEvent<String>> subscribeAlarm(String memberId, String lastEventId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/orders/alarm/subscription")
                        .queryParam("memberId", memberId)
                        .build())
                .header("contentType", "text/event-stream")
                .header("Last-Event-Id", lastEventId)
                .retrieve()
                .bodyToFlux(new ParameterizedTypeReference<>() {
                });
    }
}
