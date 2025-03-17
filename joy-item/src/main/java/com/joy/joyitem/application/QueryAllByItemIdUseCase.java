package com.joy.joyitem.application;

import com.joy.joyitem.adapters.in.QueryItemDetailsByItemIdRequest;
import com.joy.joyitem.application.dto.ItemResponse;

import java.util.List;

public interface QueryAllByItemIdUseCase {
    List<ItemResponse> query(QueryItemDetailsByItemIdRequest request);
}
