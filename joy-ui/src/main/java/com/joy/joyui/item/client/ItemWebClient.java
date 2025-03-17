package com.joy.joyui.item.client;

import com.joy.joycommon.api.response.PageDto;
import com.joy.joyui.item.dto.ItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class ItemWebClient implements ItemClient {
    private final WebClient itemClient;

    @Override
    public PageDto<ItemResponse> getAll(int page, int size) {
        return itemClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/items")
                        .queryParam("page", page)
                        .queryParam("size", size).build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<PageDto<ItemResponse>>() {
                })
                .block();
    }
}
