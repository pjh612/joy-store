package com.joy.joyadmin.item.client;

import com.joy.joyadmin.item.dto.ItemResponse;
import com.joy.joyadmin.item.dto.RegisterItemRequest;
import com.joy.joyadmin.item.dto.RegisterItemResponse;
import com.joy.joycommon.api.response.PageDto;

import java.util.UUID;

public interface ItemClient {
    PageDto<ItemResponse> getAllBySellerId(UUID sellerId, int page, int size);

    RegisterItemResponse register(RegisterItemRequest request);
}
