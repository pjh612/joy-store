package com.joy.joyapi.order.adapters.out.persistence.jpa;

import com.joy.joyapi.order.adapters.out.persistence.jpa.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemJpaRepository extends JpaRepository<OrderItemEntity, String> {
    List<OrderItemEntity> findAllByOrderId(String id);

    @Query("SELECT oi FROM OrderItemEntity oi join fetch oi.order WHERE oi.itemId IN :itemIds")
    List<OrderItemEntity> findAllOrderItemByItemIdIn(@Param("itemIds") List<String> itemIds);

    @Query("SELECT oi FROM OrderItemEntity oi JOIN FETCH oi.order WHERE oi.itemId = :itemId")
    List<OrderItemEntity> findAllByItemSeq(@Param("itemId") String itemId);
}
