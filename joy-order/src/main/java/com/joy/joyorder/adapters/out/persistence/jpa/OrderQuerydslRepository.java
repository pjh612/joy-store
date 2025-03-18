package com.joy.joyorder.adapters.out.persistence.jpa;

import com.joy.joyorder.adapters.out.persistence.jpa.entity.OrderEntity;
import com.joy.joyorder.adapters.out.persistence.jpa.entity.QOrderEntity;
import com.joy.joyorder.adapters.out.persistence.jpa.entity.QOrderItemEntity;
import com.joy.joyorder.application.usecase.criteria.QueryOrderCriteria;
import com.joy.joyorder.application.usecase.dto.OrderSummaryResponse;
import com.joy.joyorder.domain.models.OrderStatus;
import com.querydsl.core.types.NullExpression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
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

    public List<OrderEntity> find(QueryOrderCriteria criteria) {
        return jpaQueryFactory.selectFrom(QOrderEntity.orderEntity)
                .leftJoin(QOrderEntity.orderEntity.orderItemList, QOrderItemEntity.orderItemEntity).fetchJoin()
                .fetchJoin()
                .where(ltLastId(criteria.lastId()), orderStatusNotIn(criteria.excludeStatus()), QOrderItemEntity.orderItemEntity.sellerId.eq(criteria.sellerId()))
                .orderBy(orderBy(criteria.sort(), criteria.direction()))
                .limit(criteria.size())
                .fetch();
    }

    private BooleanExpression eqBuyerId(UUID buyerId) {
        if (buyerId == null) {
            return null;
        }
        return QOrderEntity.orderEntity.buyerId.eq(buyerId);
    }

    private BooleanExpression orderStatusNotIn(Collection<OrderStatus> orderStatuses) {
        if (orderStatuses == null || orderStatuses.isEmpty()) {
            return null;
        }
        return QOrderEntity.orderEntity.status.in(orderStatuses).not();
    }

    private BooleanExpression ltLastId(UUID lastId) {
        if (lastId == null) {
            return null;
        }
        return QOrderEntity.orderEntity.id.lt(lastId);
    }

    private OrderSpecifier<?> orderBy(String sort, String direction) {
        if(sort == null || sort.isEmpty()) {
            return new OrderSpecifier(Order.ASC, NullExpression.DEFAULT, OrderSpecifier.NullHandling.Default);
        }
        PathBuilder<? extends OrderEntity> pathBuilder = new PathBuilder<>(QOrderEntity.orderEntity.getType(), QOrderEntity.orderEntity.getMetadata());

        Order order = "ASC".equalsIgnoreCase(direction) ? Order.ASC : Order.DESC;

        return new OrderSpecifier<>(order, pathBuilder.getString(sort));
    }

    public OrderSummaryResponse findOrderSummaryBySellerId(UUID sellerId) {
        return jpaQueryFactory.select(Projections.constructor(
                        OrderSummaryResponse.class,
                        QOrderItemEntity.orderItemEntity.count(),
                        QOrderItemEntity.orderItemEntity.unitPrice.multiply(QOrderItemEntity.orderItemEntity.quantity).subtract(QOrderItemEntity.orderItemEntity.discountAmount).sumBigDecimal()))
                .from(QOrderItemEntity.orderItemEntity)
                .where(QOrderItemEntity.orderItemEntity.sellerId.eq(sellerId))
                .groupBy(QOrderItemEntity.orderItemEntity.sellerId)
                .fetchOne();
    }
}
