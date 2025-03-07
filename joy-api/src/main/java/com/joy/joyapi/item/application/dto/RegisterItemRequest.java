package com.joy.joyapi.item.application.dto;

import com.joy.joyapi.item.domain.models.Item;

import java.math.BigDecimal;
import java.util.UUID;

public record RegisterItemRequest(
        String title,
        String description,
        UUID sellerId,
        BigDecimal price
) {
    public Item toItem() {
        return Item.createNew(price, title, description, sellerId);
    }
}
