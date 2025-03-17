package com.joy.joyitem.domain.repository;

import com.joy.joyitem.adapters.in.ItemCriteria;
import com.joy.joyitem.domain.models.Item;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface ItemRepository {
    List<Item> findAllByIdIn(Collection<UUID> ids);

    List<Item> findAll(ItemCriteria itemCriteria);

    Long countAll();

    List<Item> findAllBySellerId(UUID sellerId);

    Item save(Item item);
}
