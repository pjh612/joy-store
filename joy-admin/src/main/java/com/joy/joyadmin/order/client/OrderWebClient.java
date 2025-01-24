package com.joy.joyadmin.order.client;

import com.joy.joyadmin.order.dto.FindOrderResponse;
import com.joy.joycommon.api.response.ApiResponse;
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
    public ApiResponse<List<FindOrderResponse>> getAllBySellerSequence(Long sequence) {
        return webClient.get()
                .uri("/api/sellers/{sellerSequence}/orders", sequence)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<List<FindOrderResponse>>>() {
                })
                .block();
    }
}
