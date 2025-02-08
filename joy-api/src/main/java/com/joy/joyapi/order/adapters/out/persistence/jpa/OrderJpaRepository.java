package com.joy.joyapi.order.adapters.out.persistence.jpa;

import com.joy.joyapi.order.adapters.out.persistence.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, String> {
    List<OrderEntity> findAllByBuyerId(String memberId);
}
