package com.joy.joyadmin.item.client;

import com.joy.joyadmin.item.dto.ItemResponse;
import com.joy.joyadmin.item.dto.RegisterItemRequest;
import com.joy.joyadmin.item.dto.RegisterItemResponse;
import com.joy.joycommon.api.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ItemWebClient implements ItemClient {
    private final WebClient webClient;

    public ApiResponse<List<ItemResponse>> getAllBySellerId(UUID sellerId) {
        return webClient.get()
                .uri("/api/items/sellers/{sellerId}", sellerId)
                .retrieve().bodyToMono(new ParameterizedTypeReference<ApiResponse<List<ItemResponse>>>() {
                })
                .block();
    }

    @Override
    public ApiResponse<RegisterItemResponse> register(RegisterItemRequest request) {
        return webClient.post()
                .uri("/api/items")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<RegisterItemResponse>>() {
                })
                .block();
    }
}
