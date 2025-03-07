package com.joy.joyapi.order.adapters.out.persistence.jpa;

import com.joy.joyapi.order.adapters.out.persistence.jpa.entity.OrderEntity;
import com.joy.joyapi.order.adapters.out.persistence.jpa.entity.QOrderEntity;
import com.joy.joyapi.order.application.usecase.criteria.QueryOrderCriteria;
import com.joy.joyapi.order.domain.models.OrderStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public class OrderQuerydslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public OrderQuerydslRepository(@Qualifier("orderJpaQueryFactory") JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<OrderEntity> findByCriteria(QueryOrderCriteria criteria) {
        return jpaQueryFactory.selectFrom(QOrderEntity.orderEntity)
                .where(eqBuyerId(criteria.buyerId()), orderStatusNotIn(criteria.excludeStatus()))
                .fetch();
    }

    private BooleanExpression eqBuyerId(UUID buyerId) {
        return QOrderEntity.orderEntity.buyerId.eq(buyerId);
    }

    private BooleanExpression orderStatusNotIn(Collection<OrderStatus> orderStatuses) {
        return QOrderEntity.orderEntity.status.in(orderStatuses).not();
    }
}
