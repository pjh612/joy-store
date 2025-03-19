package com.joy.joyorder.domain.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    private UUID id;
    private UUID buyerId;
    private OrderStatus status;
    private List<OrderItem> orderItems;
    private Instant createdAt;
    private Instant updatedAt;
    private String creator;
    private String updater;

    public static Order createNew(UUID buyerId) {
        Order order = new Order();

        order.buyerId = buyerId;
        order.status = OrderStatus.PAYMENT_WAITING;
        order.orderItems = new ArrayList<>();
        order.createdAt = Instant.now();
        order.updatedAt = Instant.now();
        order.creator = buyerId.toString();
        order.updater = buyerId.toString();
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

    public void addOrderItem(UUID itemId, String itemName, UUID sellerId, UUID couponId, BigDecimal unitPrice, Integer quantity, BigDecimal disCountPrice) {
        OrderItem orderItem = OrderItem.createNew(itemId, itemName, sellerId, this.buyerId, couponId, unitPrice, quantity);
        orderItem.applyDiscount(disCountPrice);
        this.getOrderItems().add(orderItem);
    }

    public boolean equalsToTotalPayPrice(BigDecimal totalPayPrice) {
        return this.getTotalPayPrice().compareTo(totalPayPrice) == 0;
    }

    public void completeOrder() {
        this.status = OrderStatus.ORDER_COMPLETED;
    }
}
