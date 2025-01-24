package com.joy.joyapi.item.application;

import com.joy.joyapi.item.application.dto.ItemResponse;

import java.util.List;

public interface QueryAllBySellerSequenceUseCase {
    List<ItemResponse> query(Long sellerSequence);
}
