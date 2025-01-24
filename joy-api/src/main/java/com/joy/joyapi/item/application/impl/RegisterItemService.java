package com.joy.joyapi.item.application.impl;

import com.joy.joyapi.item.application.RegisterItemUseCase;
import com.joy.joyapi.item.application.dto.RegisterItemRequest;
import com.joy.joyapi.item.application.dto.RegisterItemResponse;
import com.joy.joyapi.item.domain.models.Item;
import com.joy.joyapi.item.domain.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterItemService implements RegisterItemUseCase {
    private final ItemRepository itemRepository;

    public RegisterItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    @Transactional
    public RegisterItemResponse register(RegisterItemRequest request) {
        Item savedItem = itemRepository.save(request.toItem());

        return new RegisterItemResponse(savedItem.getSeq());
    }
}
