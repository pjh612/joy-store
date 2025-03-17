package com.joy.joyitem.application;

import com.joy.joyitem.application.dto.ItemResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface QueryAllBySellerIdUseCase {
    Page<ItemResponse> query(UUID sellerId, int size, int page);
}
