package com.joy.joyapi.order.domain.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    private Long seq;
    private Long itemSeq;
    private BigDecimal unitPrice; // 주문 상품 개당 가격
    private Integer quantity; // 주문 상품 개수
    private BigDecimal discountAmount; // 할인 금액

    private Instant createdAt;
    private Instant updatedAt;
    private String creator;
    private String updater;

    public static OrderItem createNew(Long itemSeq, Long buyerSeq, BigDecimal unitPrice, Integer quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.itemSeq = itemSeq;
        orderItem.unitPrice = unitPrice;
        orderItem.quantity = quantity;
        orderItem.createdAt = Instant.now();
        orderItem.updatedAt = Instant.now();
        orderItem.creator = buyerSeq.toString();
        orderItem.updater = buyerSeq.toString();

        return orderItem;
    }


    public BigDecimal getFinalPrice() {
        BigDecimal amount = this.unitPrice.multiply(BigDecimal.valueOf(this.quantity));
        if(discountAmount != null && discountAmount.longValue() > 0) {
            return amount.subtract(discountAmount);
        }

        return amount;
    }

    public void applyDiscount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getTotalOriginalPrice() {
        return this.unitPrice.multiply(BigDecimal.valueOf(this.quantity));
    }
}
