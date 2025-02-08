package com.joy.joyapi.order.adapters.out.persistence.jpa.entity;

import com.joy.joyapi.order.domain.models.OrderStatus;
import com.joy.joycommon.hibernate.annotations.UuidV7Generator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity {
    @Id
    @UuidV7Generator
    private String id;
    private String buyerId;
    private String couponId;
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
}
