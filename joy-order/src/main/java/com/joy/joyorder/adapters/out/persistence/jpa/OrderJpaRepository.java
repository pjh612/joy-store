package com.joy.joyorder.adapters.out.persistence.jpa;

import com.joy.joyorder.adapters.out.persistence.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {
    List<OrderEntity> findAllByBuyerId(UUID memberId);
}
