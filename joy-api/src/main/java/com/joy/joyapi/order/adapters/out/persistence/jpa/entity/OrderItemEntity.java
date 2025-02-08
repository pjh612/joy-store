package com.joy.joyapi.order.adapters.out.persistence.jpa.entity;

import com.joy.joycommon.hibernate.annotations.UuidV7Generator;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "order_item")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemEntity {
    @Id
    @UuidV7Generator
    private String id;
    private String itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;
    private BigDecimal unitPrice; // 주문 상품 개당 가격
    private Integer quantity; // 주문 상품 개수
    private BigDecimal discountAmount; // 할인 금액

    private Instant createdAt;
    private Instant updatedAt;
    private String creator;
    private String updater;
}
