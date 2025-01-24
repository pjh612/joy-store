package com.joy.joyui.order.client;

import com.joy.joycommon.api.response.ApiResponse;
import com.joy.joyui.order.dto.FindOrderResponse;
import com.joy.joyui.order.dto.PlaceOrderRequest;
import com.joy.joyui.order.dto.PlaceOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderWebClient implements OrderClient {
    private final WebClient webClient;

    @Override
    public ApiResponse<PlaceOrderResponse> placeOrder(PlaceOrderRequest request) {
        return webClient.post()
                .uri("/api/orders")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<PlaceOrderResponse>>() {
                })
                .block();
    }

    @Override
    public ApiResponse<List<FindOrderResponse>> getAllByMemberSequence(Long sequence) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/orders")
                        .queryParam("sequence", sequence)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<List<FindOrderResponse>>>() {
                })
                .block();
    }
}
