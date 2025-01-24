package com.joy.joyapi.order.domain.repository;

import com.joy.joyapi.order.domain.models.Order;

import java.util.List;

public interface OrderRepository {
    List<Order> findAllByMemberSequence(Long memberSequence);
}
