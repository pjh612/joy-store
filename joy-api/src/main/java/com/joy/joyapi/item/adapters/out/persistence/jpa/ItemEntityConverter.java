package com.joy.joyapi.item.adapters.out.persistence.jpa;

import com.joy.joyapi.item.adapters.out.persistence.jpa.entity.ItemEntity;
import com.joy.joyapi.item.domain.models.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemEntityConverter {
    public Item toDomain(ItemEntity item) {
        return new Item(item.getSeq(), item.getPrice(), item.getTitle(), item.getDescription(), item.getSellerSeq(), item.getCreatedAt(), item.getUpdatedAt(), item.getCreator(), item.getUpdater());
    }

    public ItemEntity toEntity(Item item) {
        return new ItemEntity(item.getSeq(), item.getPrice(), item.getTitle(), item.getDescription(), item.getSellerSeq(),item.getCreatedAt(),item.getUpdatedAt(), item.getCreator(), item.getUpdater());
    }
}
