package com.joy.joyui.item.client;

import com.joy.joycommon.api.response.PageDto;
import com.joy.joyui.item.dto.ItemResponse;

public interface ItemClient {
    PageDto<ItemResponse> getAll(int page, int size);
}
