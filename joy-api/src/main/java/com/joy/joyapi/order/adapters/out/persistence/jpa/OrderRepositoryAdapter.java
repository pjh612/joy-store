package com.joy.joyapi.order.adapters.out.persistence.jpa;

import com.joy.joyapi.order.adapters.out.persistence.jpa.entity.OrderEntity;
import com.joy.joyapi.order.adapters.out.persistence.jpa.entity.OrderItemEntity;
import com.joy.joyapi.order.domain.models.Order;
import com.joy.joyapi.order.domain.repository.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class OrderRepositoryAdapter implements OrderRepository {
    private final OrderJpaRepository orderJpaRepository;
    private final OrderItemJpaRepository orderItemJpaRepository;
    private final OrderEntityConverter orderEntityConverter;

    public OrderRepositoryAdapter(OrderJpaRepository orderJpaRepository, OrderItemJpaRepository orderItemJpaRepository, OrderEntityConverter orderEntityConverter) {
        this.orderJpaRepository = orderJpaRepository;
        this.orderItemJpaRepository = orderItemJpaRepository;
        this.orderEntityConverter = orderEntityConverter;
    }

    @Override
    public List<Order> findAllByMemberId(UUID memberId) {
        List<OrderEntity> orderEntities = orderJpaRepository.findAllByBuyerId(memberId);

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
}
