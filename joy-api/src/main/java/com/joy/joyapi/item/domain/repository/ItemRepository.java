package com.joy.joyapi.item.domain.repository;

import com.joy.joyapi.item.domain.models.Item;

import java.util.List;

public interface ItemRepository {
    List<Item> findAllBySequenceIn(List<String> ids);

    List<Item> findAll();

    List<Item> findAllBySellerId(String sellerId);

    Item save(Item item);
}
