package com.joy.joyitem.application.impl;

import com.joy.joyitem.adapters.in.QueryItemDetailsByItemIdRequest;
import com.joy.joyitem.application.QueryAllByItemIdUseCase;
import com.joy.joyitem.application.dto.ItemResponse;
import com.joy.joyitem.domain.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryAllByItemIdService implements QueryAllByItemIdUseCase {
    private final ItemRepository itemRepository;
    @Override
    public List<ItemResponse> query(QueryItemDetailsByItemIdRequest request) {
        return itemRepository.findAllByIdIn(request.itemIds())
                .stream()
                .map(ItemResponse::of)
                .toList();
    }
}
