package com.joy.joyorder.application.client;

import com.joy.joyorder.application.client.dto.ItemResponse;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface ItemClient {
    List<ItemResponse> findAllByIdIn(Collection<UUID> itemIds);

    List<ItemResponse> findAllBySellerId(UUID sellerId);
}
