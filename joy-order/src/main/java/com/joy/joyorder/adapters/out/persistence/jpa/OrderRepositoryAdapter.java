package com.joy.joyorder.adapters.out.persistence.jpa;

import com.joy.joyorder.adapters.out.persistence.jpa.OrderJpaRepository;
import com.joy.joyorder.adapters.out.persistence.jpa.OrderQuerydslRepository;
import com.joy.joyorder.adapters.out.persistence.jpa.entity.OrderEntity;
import com.joy.joyorder.adapters.out.persistence.jpa.entity.OrderItemEntity;
import com.joy.joyorder.application.usecase.criteria.QueryOrderBySellerIdCriteria;
import com.joy.joyorder.application.usecase.criteria.QueryOrderCriteria;
import com.joy.joyorder.domain.models.Order;
import com.joy.joyorder.domain.models.OrderStatus;
import com.joy.joyorder.domain.repository.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class OrderRepositoryAdapter implements OrderRepository {
    private final OrderJpaRepository orderJpaRepository;
    private final OrderQuerydslRepository orderQuerydslRepository;
    private final OrderItemJpaRepository orderItemJpaRepository;
    private final OrderEntityConverter orderEntityConverter;

    public OrderRepositoryAdapter(OrderJpaRepository orderJpaRepository, OrderQuerydslRepository orderQuerydslRepository, OrderItemJpaRepository orderItemJpaRepository, OrderEntityConverter orderEntityConverter) {
        this.orderJpaRepository = orderJpaRepository;
        this.orderQuerydslRepository = orderQuerydslRepository;
        this.orderItemJpaRepository = orderItemJpaRepository;
        this.orderEntityConverter = orderEntityConverter;
    }

    public List<Order> findByCriteria(QueryOrderCriteria criteria) {
        List<OrderEntity> orderEntities = orderQuerydslRepository.findByCriteria(criteria);

        return orderEntities.stream()
                .map(it -> orderEntityConverter.toOrderDomain(it, orderItemJpaRepository.findAllByOrderId(it.getId())))
                .toList();
    }

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = orderEntityConverter.toOrderEntity(order);
        OrderEntity savedOrder = orderJpaRepository.save(orderEntity);

        return orderEntityConverter.toOrderDomain(savedOrder, savedOrder.getOrderItemList());
    }

    @Override
    public List<Order> findAllByItemIdsIn(List<UUID> itemIds) {
        List<OrderEntity> orderEntities = orderItemJpaRepository.findAllOrderItemByItemIdIn(itemIds)
                .stream()
                .map(OrderItemEntity::getOrder)
                .toList();

        return orderEntities.stream()
                .map(it -> orderEntityConverter.toOrderDomain(it, it.getOrderItemList()))
                .toList();
    }

    @Override
    public List<Order> findAllByItemId(UUID itemId) {
        return orderItemJpaRepository.findAllByItemSeq(itemId)
                .stream()
                .map(it -> orderEntityConverter.toOrderDomain(it.getOrder(), it.getOrder().getOrderItemList()))
                .toList();
    }

    @Override
    public Optional<Order> findById(UUID orderId) {
        return orderJpaRepository.findById(orderId)
                .map(it -> orderEntityConverter.toOrderDomain(it, orderItemJpaRepository.findAllByOrderId(it.getId())));
    }

    @Override
    public List<Order> findBySellerId(QueryOrderBySellerIdCriteria criteria) {
        List<OrderEntity> orderEntities = orderQuerydslRepository.find(new QueryOrderCriteria(null, criteria.sellerId(), List.of(OrderStatus.PAYMENT_WAITING), null, criteria.lastId(), criteria.sort(), criteria.direction(), criteria.size()));
        return orderEntities.stream()
                .map(it -> orderEntityConverter.toOrderDomain(it, orderItemJpaRepository.findAllByOrderId(it.getId())))
                .toList();
    }
}
