package com.joy.joyitem.application.impl;

import com.joy.joyitem.application.QueryAllBySellerIdUseCase;
import com.joy.joyitem.application.dto.ItemResponse;
import com.joy.joyitem.domain.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class QueryAllBySellerIdService implements QueryAllBySellerIdUseCase {
    private final ItemRepository itemRepository;

    public QueryAllBySellerIdService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Page<ItemResponse> query(UUID sellerId, int size, int page) {
        PageRequest pageRequest = PageRequest.of(page, size);

        Long totalCount = itemRepository.countBySellerId(sellerId);

        return new PageImpl<>(itemRepository.findAllBySellerId(sellerId, size, pageRequest.getOffset())
                .stream()
                .map(ItemResponse::of)
                .toList(), pageRequest, totalCount);
    }
}
