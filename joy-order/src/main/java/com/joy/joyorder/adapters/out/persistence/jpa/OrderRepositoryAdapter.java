package com.joy.joyorder.adapters.out.persistence.jpa;

import com.joy.joyorder.adapters.out.persistence.jpa.entity.OrderEntity;
import com.joy.joyorder.adapters.out.persistence.jpa.entity.OrderItemEntity;
import com.joy.joyorder.domain.models.Order;
import com.joy.joyorder.domain.repository.OrderRepository;
import com.joy.joyorder.domain.repository.criteria.QueryOrderBySellerIdCriteria;
import com.joy.joyorder.domain.repository.criteria.QueryOrderCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
        Map<UUID, List<OrderItemEntity>> orderItemMap = loadOrderItemsByOrder(orderEntities);

        for (OrderEntity orderEntity : orderEntities) {
            orderEntity.setOrderItemList(orderItemMap.get(orderEntity.getId()));
        }

        return orderEntities.stream()
                .map(orderEntityConverter::toOrderDomain)
                .toList();
    }

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = orderEntityConverter.toOrderEntity(order);
        OrderEntity savedOrder = orderJpaRepository.save(orderEntity);

        return orderEntityConverter.toOrderDomain(savedOrder);
    }

    @Override
    public Optional<Order> findById(UUID orderId) {
        return orderJpaRepository.findById(orderId)
                .map(orderEntity -> {
                    orderEntity.setOrderItemList(orderItemJpaRepository.findAllByOrderId(orderId));
                    return orderEntityConverter.toOrderDomain(orderEntity);
                });
    }

    @Override
    public List<Order> findBySellerId(QueryOrderBySellerIdCriteria criteria) {
        List<OrderEntity> orderEntities = orderQuerydslRepository.findBySellerId(criteria);
        Map<UUID, List<OrderItemEntity>> orderItemMap = loadOrderItemsByOrder(orderEntities);

        for (OrderEntity orderEntity : orderEntities) {
            orderEntity.setOrderItemList(orderItemMap.get(orderEntity.getId()));
        }

        return orderEntities.stream()
                .map(orderEntityConverter::toOrderDomain)
                .toList();
    }

    private Map<UUID, List<OrderItemEntity>> loadOrderItemsByOrder(List<OrderEntity> orderEntities) {
        return loadOrderItemsByOrderIds(orderEntities.stream()
                .map(OrderEntity::getId)
                .toList());
    }

    private Map<UUID, List<OrderItemEntity>> loadOrderItemsByOrderIds(List<UUID> orderIds) {
        return orderItemJpaRepository.findAllByOrderIdIn(orderIds)
                .stream()
                .collect(Collectors.groupingBy(item -> item.getOrder().getId()));
    }

}
