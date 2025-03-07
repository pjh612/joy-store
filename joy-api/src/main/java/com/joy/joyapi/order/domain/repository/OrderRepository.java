package com.joy.joyapi.order.domain.repository;

import com.joy.joyapi.order.domain.models.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    List<Order> findAllByMemberId(UUID memberId);

    Order save(Order order);

    List<Order> findAllByItemIdsIn(List<UUID> itemIds);

    List<Order> findAllByItemId(UUID itemId);
}
