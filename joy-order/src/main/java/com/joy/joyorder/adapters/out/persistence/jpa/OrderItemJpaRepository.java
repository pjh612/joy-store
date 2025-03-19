package com.joy.joyorder.adapters.out.persistence.jpa;

import com.joy.joyorder.adapters.out.persistence.jpa.entity.OrderEntity;
import com.joy.joyorder.adapters.out.persistence.jpa.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface OrderItemJpaRepository extends JpaRepository<OrderItemEntity, UUID> {
    List<OrderItemEntity> findAllByOrderId(UUID id);

    @Query("SELECT oi FROM OrderItemEntity oi join fetch oi.order WHERE oi.order.id IN :id")
    List<OrderItemEntity> findAllByOrderIdIn(Collection<UUID> id);

    UUID order(OrderEntity order);
}
