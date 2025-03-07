package com.joy.joyapi.item.application;

import com.joy.joyapi.item.application.dto.ItemResponse;

import java.util.List;
import java.util.UUID;

public interface QueryAllBySellerSequenceUseCase {
    List<ItemResponse> query(UUID sellerId);
}
