package com.joy.joyitem.adapters.out.persistence.jpa;

import com.joy.joyitem.adapters.in.ItemCriteria;
import com.joy.joyitem.adapters.out.persistence.jpa.entity.ItemEntity;
import com.joy.joyitem.adapters.out.persistence.jpa.entity.QItemEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ItemQuerydslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public Page<ItemEntity> findAll(ItemCriteria itemCriteria) {
        return new PageImpl<>(jpaQueryFactory.selectFrom(QItemEntity.itemEntity)
                .offset((long)itemCriteria.page() * (long)itemCriteria.size())
                .limit(itemCriteria.size())
                .fetch());
    }

    public Long countAll() {
        return jpaQueryFactory.select(QItemEntity.itemEntity.count())
                .from(QItemEntity.itemEntity)
                .fetchOne();
    }

    public Page<ItemEntity> findAllBySellerId(UUID sellerId, int size, long offset) {
        return new PageImpl<>(jpaQueryFactory.selectFrom(QItemEntity.itemEntity)
                .where(QItemEntity.itemEntity.sellerId.eq(sellerId))
                .offset(offset)
                .limit(size)
                .fetch());
    }

    public Long countBySellerId(UUID sellerId) {
        return jpaQueryFactory.select(QItemEntity.itemEntity.count())
                .from(QItemEntity.itemEntity)
                .where(QItemEntity.itemEntity.sellerId.eq(sellerId))
                .fetchOne();
    }
}
