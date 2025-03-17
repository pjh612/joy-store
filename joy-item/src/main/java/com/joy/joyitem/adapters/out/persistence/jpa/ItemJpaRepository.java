package com.joy.joyitem.adapters.out.persistence.jpa;

import com.joy.joyitem.adapters.out.persistence.jpa.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface ItemJpaRepository extends JpaRepository<ItemEntity, UUID> {
    Collection<ItemEntity> findAllByIdIn(Collection<UUID> ids);

    List<ItemEntity> findAllBySellerId(UUID sellerId);

}
