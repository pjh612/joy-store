package com.joy.joyapi.order.domain.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    private Long seq;
    private Long buyerSequence;
    private OrderStatus status;
    private List<OrderItem> orderItems;
    private Long couponSeq;
    private Instant createdAt;
    private Instant updatedAt;
    private String creator;
    private String updater;

    public static Order createNew(Long buyerSeq) {
        Order order = new Order();

        order.buyerSequence = buyerSeq;
        order.status = OrderStatus.DELIVERY_PREPARING;
        order.orderItems = new ArrayList<>();
        order.createdAt = Instant.now();
        order.updatedAt = Instant.now();
        order.creator = buyerSeq.toString();
        order.updater = buyerSeq.toString();
        return order;
    }

    public BigDecimal getTotalPayPrice() {
        BigDecimal totalPayPrice = BigDecimal.ZERO;
        List<BigDecimal> payPrices = this.orderItems.stream()
                .map(OrderItem::getFinalPrice)
                .toList();
        for (BigDecimal payPrice : payPrices) {
            totalPayPrice = totalPayPrice.add(payPrice);
        }

        return totalPayPrice;

    }

    public void addOrderItem(Long itemSequence, BigDecimal unitPrice, Integer quantity) {
        BigDecimal originalPrice = unitPrice.multiply(BigDecimal.valueOf(quantity));
        OrderItem orderItem = OrderItem.createNew(itemSequence, this.buyerSequence, originalPrice, quantity);
        this.getOrderItems().add(orderItem);
    }

    public void applyCoupon(Long couponSeq, Map<Long, BigDecimal> discountedPrices) {
        this.couponSeq = couponSeq;
        for (OrderItem orderItem : orderItems) {
            BigDecimal discountedPrice = discountedPrices.get(orderItem.getItemSeq());
            orderItem.applyDiscount(discountedPrice);
        }
    }

    public Map<Long, BigDecimal> getOriginalPrices() {
        return orderItems.stream().collect(Collectors.toMap(OrderItem::getItemSeq, OrderItem::getTotalOriginalPrice));
    }
}
