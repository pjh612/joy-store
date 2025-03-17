package com.joy.joyorder.adapters.out.client;

import com.joy.joyorder.application.client.ItemClient;
import com.joy.joyorder.application.client.dto.FindItemRequest;
import com.joy.joyorder.application.client.dto.ItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ItemClientAdapter implements ItemClient {
    private final RestClient itemClient;

    @Override
    public List<ItemResponse> findAllByIdIn(Collection<UUID> itemIds) {
        return itemClient.post()
                .uri("/api/items/details")
                .body(new FindItemRequest(itemIds))
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public List<ItemResponse> findAllBySellerId(UUID sellerId) {
        return List.of();
    }
}
