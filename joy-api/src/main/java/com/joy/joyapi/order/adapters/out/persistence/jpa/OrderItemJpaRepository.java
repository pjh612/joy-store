package com.joy.joyapi.order.adapters.out.persistence.jpa;

import com.joy.joyapi.order.adapters.out.persistence.jpa.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemJpaRepository extends JpaRepository<OrderItemEntity, Long> {
    List<OrderItemEntity> findAllByOrderSeq(Long seq);

    @Query("SELECT oi FROM OrderItemEntity oi join fetch oi.order WHERE oi.itemSeq IN :itemSeq")
    List<OrderItemEntity> findAllOrderItemByItemSeqIn(@Param("itemSeq") List<Long> itemSeq);
}
