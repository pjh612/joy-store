package com.joy.joyapi.order.domain.repository;

import com.joy.joyapi.order.domain.models.Order;

import java.util.List;

public interface OrderRepository {
    List<Order> findAllByMemberId(String memberId);

    Order save(Order order);

    List<Order> findAllByItemSequenceIn(List<String> itemSequences);

    List<Order> findAllByItemSequence(String itemSequence);
}
