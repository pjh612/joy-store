package com.joy.joyorder.adapters.out.persistence.jpa.entity;

import com.joy.joycommon.hibernate.annotations.UuidV7Generator;
import com.joy.joyorder.domain.models.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity {
    @Id
    @UuidV7Generator
    private UUID id;
    private UUID buyerId;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    List<OrderItemEntity> orderItemList = new ArrayList<>();
    private Instant createdAt;
    private Instant updatedAt;
    private String creator;
    private String updater;

    public void addOrderItem(List<OrderItemEntity> orderItemList) {
        if (orderItemList == null) {
            return;
        }

        this.orderItemList.addAll(orderItemList);
        for (OrderItemEntity orderItemEntity : orderItemList) {
            orderItemEntity.setOrder(this);
        }
    }

    public void setOrderItemList(List<OrderItemEntity> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
