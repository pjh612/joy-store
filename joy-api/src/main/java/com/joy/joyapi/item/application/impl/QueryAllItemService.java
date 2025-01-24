package com.joy.joyapi.item.application.impl;

import com.joy.joyapi.item.application.QueryAllItemUseCase;
import com.joy.joyapi.item.application.dto.ItemResponse;
import com.joy.joyapi.item.domain.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryAllItemService implements QueryAllItemUseCase {
    private final ItemRepository itemRepository;

    public QueryAllItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<ItemResponse> query() {
        return itemRepository.findAll()
                .stream()
                .map(ItemResponse::of)
                .toList();
    }
}
