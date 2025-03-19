package com.joy.joyorder.domain.repository;

import com.joy.joyorder.domain.repository.criteria.QueryOrderBySellerIdCriteria;
import com.joy.joyorder.domain.repository.criteria.QueryOrderCriteria;
import com.joy.joyorder.domain.models.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    List<Order> findByCriteria(QueryOrderCriteria criteria);

    Order save(Order order);

    Optional<Order> findById(UUID orderId);

    List<Order> findBySellerId(QueryOrderBySellerIdCriteria criteria);
}
