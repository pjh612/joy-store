package com.joy.joyapi.item.domain.repository;

import com.joy.joyapi.item.domain.models.Item;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface ItemRepository {
    List<Item> findAllByIdIn(Collection<UUID> ids);

    List<Item> findAll();

    List<Item> findAllBySellerId(UUID sellerId);

    Item save(Item item);
}
