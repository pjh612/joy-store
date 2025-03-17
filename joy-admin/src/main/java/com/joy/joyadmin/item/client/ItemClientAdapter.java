package com.joy.joyadmin.item.client;

import com.joy.joyadmin.item.dto.ItemResponse;
import com.joy.joyadmin.item.dto.RegisterItemRequest;
import com.joy.joyadmin.item.dto.RegisterItemResponse;
import com.joy.joycommon.api.response.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ItemClientAdapter implements ItemClient {
    private final WebClient itemWebClient;

    public PageDto<ItemResponse> getAllBySellerId(UUID sellerId, int page, int size) {
        return itemWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/items/sellers/" + sellerId)
                        .queryParam("page", page)
                        .queryParam("size", size)
                        .build())
                .retrieve().bodyToMono(new ParameterizedTypeReference<PageDto<ItemResponse>>() {
                })
                .block();
    }

    @Override
    public RegisterItemResponse register(RegisterItemRequest request) {
        return itemWebClient.post()
                .uri("/api/items")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<RegisterItemResponse>() {
                })
                .block();
    }
}
