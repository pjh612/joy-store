package com.joy.joyui.item.client;

import com.joy.joycommon.api.response.ApiResponse;
import com.joy.joyui.item.dto.ItemResponse;

import java.util.List;

public interface ItemClient {
    ApiResponse<List<ItemResponse>> getAll();
}
