package com.joy.joyitem.application;

import com.joy.joyitem.application.dto.ItemResponse;

import java.util.List;
import java.util.UUID;

public interface QueryAllBySellerSequenceUseCase {
    List<ItemResponse> query(UUID sellerId);
}
