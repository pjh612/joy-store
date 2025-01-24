package com.joy.joyapi.order.adapters.out.persistence.jpa.entity;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private Long itemSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_seq")
    private OrderEntity order;
    private BigDecimal unitPrice; // 주문 상품 개당 가격
    private Integer quantity; // 주문 상품 개수
    private BigDecimal discountAmount; // 할인 금액

    private Instant createdAt;
    private Instant updatedAt;
    private String creator;
    private String updater;
}
