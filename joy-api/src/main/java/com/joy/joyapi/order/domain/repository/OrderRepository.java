package com.joy.joyapi.order.domain.repository;

import com.joy.joyapi.order.domain.models.Order;

import java.util.List;

public interface OrderRepository {
    List<Order> findAllByMemberSequence(Long memberSequence);

    Order save(Order order);

    List<Order> findAllByItemSequenceIn(List<Long> itemSequences);

    List<Order> findAllByItemSequence(Long itemSequence);
}
