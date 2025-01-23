package com.joy.joyapi.item.domain.repository;

import com.joy.joyapi.item.domain.models.Item;

import java.util.List;

public interface ItemRepository {
    List<Item> findAllBySequenceIn(List<Long> itemSequences);

    List<Item> findAll();

    List<Item> findAllBySellerSequence(Long sellerSequence);

    Item save(Item item);
}
