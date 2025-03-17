package com.joy.joyitem.application.impl;

import com.joy.joyitem.application.RegisterItemUseCase;
import com.joy.joyitem.application.dto.RegisterItemRequest;
import com.joy.joyitem.application.dto.RegisterItemResponse;
import com.joy.joyitem.domain.models.Item;
import com.joy.joyitem.domain.repository.ItemRepository;
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

        return new RegisterItemResponse(savedItem.getId());
    }
}
