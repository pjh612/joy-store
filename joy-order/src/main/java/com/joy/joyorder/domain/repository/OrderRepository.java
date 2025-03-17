package com.joy.joyorder.domain.repository;

import com.joy.joyorder.application.usecase.criteria.QueryOrderBySellerIdCriteria;
import com.joy.joyorder.application.usecase.criteria.QueryOrderCriteria;
import com.joy.joyorder.domain.models.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    List<Order> findByCriteria(QueryOrderCriteria criteria);

    Order save(Order order);

    List<Order> findAllByItemIdsIn(List<UUID> itemIds);

    List<Order> findAllByItemId(UUID itemId);

    Optional<Order> findById(UUID orderId);

    List<Order> findBySellerId(QueryOrderBySellerIdCriteria criteria);
}
