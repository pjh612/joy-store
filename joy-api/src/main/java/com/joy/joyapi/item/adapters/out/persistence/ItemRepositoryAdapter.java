package com.joy.joyapi.item.adapters.out.persistence;

import com.joy.joyapi.item.adapters.out.persistence.jpa.ItemEntityConverter;
import com.joy.joyapi.item.adapters.out.persistence.jpa.ItemJpaRepository;
import com.joy.joyapi.item.adapters.out.persistence.jpa.entity.ItemEntity;
import com.joy.joyapi.item.domain.models.Item;
import com.joy.joyapi.item.domain.repository.ItemRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public class ItemRepositoryAdapter implements ItemRepository {
    private final ItemJpaRepository itemJpaRepository;
    private final ItemEntityConverter itemEntityConverter;

    public ItemRepositoryAdapter(ItemJpaRepository itemJpaRepository, ItemEntityConverter itemEntityConverter) {
        this.itemJpaRepository = itemJpaRepository;
        this.itemEntityConverter = itemEntityConverter;
    }

    @Override
    public List<Item> findAllByIdIn(Collection<UUID> ids) {
        return itemJpaRepository.findAllByIdIn(ids)
                .stream()
                .map(itemEntityConverter::toDomain)
                .toList();
    }

    @Override
    public List<Item> findAll() {
        return itemJpaRepository.findAll()
                .stream()
                .map(itemEntityConverter::toDomain)
                .toList();
    }

    @Override
    public List<Item> findAllBySellerId(UUID sellerId) {
        return itemJpaRepository.findAllBySellerId(sellerId)
                .stream()
                .map(itemEntityConverter::toDomain)
                .toList();

    }

    @Override
    public Item save(Item item) {
        ItemEntity itemEntity = itemEntityConverter.toEntity(item);
        ItemEntity savedItemEntity = itemJpaRepository.save(itemEntity);

        return itemEntityConverter.toDomain(savedItemEntity);
    }
}
