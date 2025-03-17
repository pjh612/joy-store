package com.joy.joyitem.adapters.out.persistence.jpa;

import com.joy.joyitem.adapters.in.ItemCriteria;
import com.joy.joyitem.adapters.out.persistence.jpa.entity.ItemEntity;
import com.joy.joyitem.domain.models.Item;
import com.joy.joyitem.domain.repository.ItemRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public class ItemRepositoryAdapter implements ItemRepository {
    private final ItemJpaRepository itemJpaRepository;
    private final ItemQuerydslRepository itemQuerydslRepository;
    private final ItemEntityConverter itemEntityConverter;

    public ItemRepositoryAdapter(ItemJpaRepository itemJpaRepository, ItemQuerydslRepository itemQuerydslRepository, ItemEntityConverter itemEntityConverter) {
        this.itemJpaRepository = itemJpaRepository;
        this.itemQuerydslRepository = itemQuerydslRepository;
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
    public List<Item> findAll(ItemCriteria itemCriteria) {
        return itemQuerydslRepository.findAll(itemCriteria)
                .stream()
                .map(itemEntityConverter::toDomain)
                .toList();
    }

    @Override
    public Long countAll() {
        return itemQuerydslRepository.countAll();
    }

    @Override
    public List<Item> findAllBySellerId(UUID sellerId, int size, long offset) {
        return itemQuerydslRepository.findAllBySellerId(sellerId, size, offset)
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

    @Override
    public Long countBySellerId(UUID sellerId) {
        return itemQuerydslRepository.countBySellerId(sellerId);
    }
}
