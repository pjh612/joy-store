package com.joy.joyapi.order.adapters.out.persistence.jpa;

import com.joy.joyapi.order.adapters.out.persistence.jpa.entity.OrderEntity;
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
    public List<Order> findAllByMemberSequence(Long memberSequence) {
        List<OrderEntity> orderEntities = orderJpaRepository.findAllByBuyerSequence(memberSequence);

        return orderEntities.stream()
                .map(it -> orderEntityConverter.toOrderDomain(it, orderItemJpaRepository.findAllByOrderSeq(it.getSeq())))
                .toList();
    }
}
