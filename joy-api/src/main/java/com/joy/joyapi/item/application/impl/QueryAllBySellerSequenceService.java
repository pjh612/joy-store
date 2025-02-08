package com.joy.joyapi.item.application.impl;

import com.joy.joyapi.item.application.QueryAllBySellerSequenceUseCase;
import com.joy.joyapi.item.application.dto.ItemResponse;
import com.joy.joyapi.item.domain.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryAllBySellerSequenceService implements QueryAllBySellerSequenceUseCase {
    private final ItemRepository itemRepository;

    public QueryAllBySellerSequenceService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<ItemResponse> query(String sellerId) {
        return itemRepository.findAllBySellerId(sellerId)
                .stream()
                .map(ItemResponse::of)
                .toList();
    }
}
