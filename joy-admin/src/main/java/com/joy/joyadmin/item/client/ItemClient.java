package com.joy.joyadmin.item.client;

import com.joy.joyadmin.item.dto.ItemResponse;
import com.joy.joyadmin.item.dto.RegisterItemRequest;
import com.joy.joyadmin.item.dto.RegisterItemResponse;
import com.joy.joycommon.api.response.ApiResponse;

import java.util.List;

public interface ItemClient {
    ApiResponse<List<ItemResponse>> getAllBySellerId(String sellerId);

    ApiResponse<RegisterItemResponse> register(RegisterItemRequest request);
}
