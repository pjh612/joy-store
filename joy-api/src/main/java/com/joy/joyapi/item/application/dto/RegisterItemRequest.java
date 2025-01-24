package com.joy.joyapi.item.application.dto;

import com.joy.joyapi.item.domain.models.Item;

import java.math.BigDecimal;

public record RegisterItemRequest(
        String title,
        String description,
        Long sellerSeq,
        BigDecimal price
) {
    public Item toItem() {
        return Item.createNew(price, title, description, sellerSeq);
    }
}
