package com.joy.joyapi.item.adapters.out.persistence.jpa;

import com.joy.joyapi.item.adapters.out.persistence.jpa.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ItemJpaRepository extends JpaRepository<ItemEntity, String> {
    Collection<ItemEntity> findAllByIdIn(List<String> ids);

    List<ItemEntity> findAllBySellerId(String sellerId);

}
