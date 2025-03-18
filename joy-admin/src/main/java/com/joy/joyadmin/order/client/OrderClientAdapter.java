package com.joy.joyadmin.order.client;

import com.joy.joyadmin.order.dto.FindOrderResponse;
import com.joy.joycommon.api.response.ApiResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

@Component

public class OrderClientAdapter implements OrderClient {
    private final WebClient orderWebClient;

    public OrderClientAdapter(@Qualifier("orderWebClient") WebClient orderWebClient) {
        this.orderWebClient = orderWebClient;
    }

    @Override
    public ApiResponse<List<FindOrderResponse>> getAllBySellerId(UUID sellerId, UUID lastId, int size) {
        return orderWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/sellers/" + sellerId + "/orders")
                        .queryParam("size", size)
                        .queryParam("lastId", lastId)
                        .queryParam("sellerId", sellerId)
                        .queryParam("sort", "id")
                        .queryParam("direction", "DESC")
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<List<FindOrderResponse>>>() {
                })
                .block();
    }

    @Override
    public Flux<ServerSentEvent<String>> subscribeAlarm(UUID memberId, String lastEventId) {
        return orderWebClient.get()
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
