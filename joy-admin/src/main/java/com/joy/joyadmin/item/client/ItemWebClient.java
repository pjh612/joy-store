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

@Component
@RequiredArgsConstructor
public class ItemWebClient implements ItemClient {
    private final WebClient webClient;

    @Override
    public ApiResponse<List<ItemResponse>> getAllBySellerSequence(Long sellerSequence) {
        return webClient.get()
                .uri("/api/items/sellers/{sellerSequence}", sellerSequence)
                .retrieve().bodyToMono(new ParameterizedTypeReference<ApiResponse<List<ItemResponse>>>() {
                })
                .block();
    }
}
