package com.joy.joyapi.order.adapters.out.persistence.jpa;

import com.joy.joyapi.order.adapters.out.persistence.jpa.entity.OrderEntity;
import com.joy.joyapi.order.adapters.out.persistence.jpa.entity.QOrderEntity;
import com.joy.joyapi.order.application.usecase.criteria.QueryOrderCriteria;
import com.joy.joyapi.order.domain.models.OrderStatus;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
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
                .where(eqBuyerId(criteria.buyerId()), orderStatusNotIn(criteria.excludeStatus()), ltLastId(criteria.lastId()))
                .orderBy(orderBy(criteria.sort(), criteria.direction()))
                .limit(criteria.size())
                .fetch();
    }

    private BooleanExpression eqBuyerId(UUID buyerId) {
        if(buyerId == null) {
            return null;
        }
        return QOrderEntity.orderEntity.buyerId.eq(buyerId);
    }

    private BooleanExpression orderStatusNotIn(Collection<OrderStatus> orderStatuses) {
        if(orderStatuses == null || orderStatuses.isEmpty()) {
            return null;
        }
        return QOrderEntity.orderEntity.status.in(orderStatuses).not();
    }

    private BooleanExpression ltLastId(UUID lastId) {
        if(lastId == null) {
            return null;
        }
        return QOrderEntity.orderEntity.id.lt(lastId);
    }

    private OrderSpecifier<?> orderBy(String sort, String direction) {
        PathBuilder<? extends OrderEntity> pathBuilder = new PathBuilder<>(QOrderEntity.orderEntity.getType(), QOrderEntity.orderEntity.getMetadata());

        Order order = "ASC".equalsIgnoreCase(direction) ? Order.ASC : Order.DESC;

        return new OrderSpecifier<>(order, pathBuilder.getString(sort));
    }
}
