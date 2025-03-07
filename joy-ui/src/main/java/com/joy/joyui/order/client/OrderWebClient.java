package com.joy.joyui.order.client;

import com.joy.joycommon.api.response.ApiResponse;
import com.joy.joyui.order.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderWebClient implements OrderClient {
    private final WebClient webClient;

    @Override
    public ApiResponse<ConfirmOrderResponse> confirmOrder(ConfirmOrderRequest request) {
        return webClient.post()
                .uri("/api/orders")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<ConfirmOrderResponse>>() {
                })
                .block();
    }

    @Override
    public ApiResponse<List<FindOrderResponse>> getByCriteria(UUID buyerId, Collection<String> excludeStatus) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/orders")
                        .queryParam("buyerId", buyerId)
                        .queryParam("excludeStatus", excludeStatus)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<List<FindOrderResponse>>>() {
                })
                .block();
    }

    @Override
    public ApiResponse<CreateProvisionalOrderResponse> createProvisionalOrder(CreateProvisionalOrderCommand request) {
        return webClient.post()
                .uri("/api/orders/provisional")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<CreateProvisionalOrderResponse>>() {
                })
                .block();
    }

    @Override
    public ApiResponse<FindOrderResponse> getByOrderId(UUID orderId) {
        return webClient.get()
                .uri("/api/orders/{orderId}", orderId.toString())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<FindOrderResponse>>() {
                })
                .block();

    }
}
