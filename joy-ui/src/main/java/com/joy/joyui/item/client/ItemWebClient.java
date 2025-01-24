package com.joy.joyui.item.client;

import com.joy.joycommon.api.response.ApiResponse;
import com.joy.joyui.item.dto.ItemResponse;
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
    public ApiResponse<List<ItemResponse>> getAll() {
        return webClient.get()
                .uri("/api/items")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<List<ItemResponse>>>() {
                })
                .block();
    }
}
