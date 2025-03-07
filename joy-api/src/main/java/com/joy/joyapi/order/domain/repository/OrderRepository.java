package com.joy.joyapi.order.domain.repository;

import com.joy.joyapi.order.application.usecase.criteria.QueryOrderCriteria;
import com.joy.joyapi.order.domain.models.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    List<Order> findByCriteria(QueryOrderCriteria criteria);

    Order save(Order order);

    List<Order> findAllByItemIdsIn(List<UUID> itemIds);

    List<Order> findAllByItemId(UUID itemId);

    Optional<Order> findById(UUID orderId);
}
