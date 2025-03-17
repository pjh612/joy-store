package com.joy.joyitem.application.impl;

import com.joy.joyitem.adapters.in.ItemCriteria;
import com.joy.joyitem.application.QueryAllItemUseCase;
import com.joy.joyitem.application.dto.ItemResponse;
import com.joy.joyitem.domain.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class QueryAllItemService implements QueryAllItemUseCase {
    private final ItemRepository itemRepository;

    public QueryAllItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Page<ItemResponse> query(ItemCriteria itemCriteria) {
        PageRequest pageRequest = PageRequest.of(itemCriteria.page(), itemCriteria.size());

        Long totalCount = itemRepository.countAll();

        return new PageImpl<>(itemRepository.findAll(itemCriteria)
                .stream()
                .map(ItemResponse::of)
                .toList(), pageRequest, totalCount);
    }
}
