package com.joy.joyapi.order.adapters.out.persistence.jpa;

import com.joy.joyapi.order.adapters.out.persistence.jpa.entity.OrderEntity;
import com.joy.joyapi.order.adapters.out.persistence.jpa.entity.OrderItemEntity;
import com.joy.joyapi.order.domain.models.Order;
import com.joy.joyapi.order.domain.repository.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public List<Order> findAllByMemberId(String memberId) {
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
    public List<Order> findAllByItemSequenceIn(List<String> itemSequences) {
        List<OrderEntity> orderEntities = orderItemJpaRepository.findAllOrderItemByItemIdIn(itemSequences)
                .stream()
                .map(OrderItemEntity::getOrder)
                .toList();

        return orderEntities.stream()
                .map(it -> orderEntityConverter.toOrderDomain(it, it.getOrderItemList()))
                .toList();
    }

    @Override
    public List<Order> findAllByItemSequence(String itemSequence) {
        return orderItemJpaRepository.findAllByItemSeq(itemSequence)
                .stream()
                .map(it -> orderEntityConverter.toOrderDomain(it.getOrder(), it.getOrder().getOrderItemList()))
                .toList();
    }
}
