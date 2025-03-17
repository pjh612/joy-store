package com.joy.joyitem.application.impl;

import com.joy.joyitem.application.QueryAllBySellerSequenceUseCase;
import com.joy.joyitem.application.dto.ItemResponse;
import com.joy.joyitem.domain.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QueryAllBySellerSequenceService implements QueryAllBySellerSequenceUseCase {
    private final ItemRepository itemRepository;

    public QueryAllBySellerSequenceService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<ItemResponse> query(UUID sellerId) {
        return itemRepository.findAllBySellerId(sellerId)
                .stream()
                .map(ItemResponse::of)
                .toList();
    }
}
