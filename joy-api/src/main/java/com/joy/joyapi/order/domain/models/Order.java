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
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    private UUID id;
    private UUID buyerId;
    private OrderStatus status;
    private List<OrderItem> orderItems;
    private UUID couponId;
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

    public void addOrderItem(UUID itemId, BigDecimal unitPrice, Integer quantity) {
        BigDecimal originalPrice = unitPrice.multiply(BigDecimal.valueOf(quantity));
        OrderItem orderItem = OrderItem.createNew(itemId, this.buyerId, originalPrice, quantity);
        this.getOrderItems().add(orderItem);
    }

    public void applyCoupon(UUID couponId, Map<UUID, BigDecimal> discountedPrices) {
        this.couponId = couponId;
        for (OrderItem orderItem : orderItems) {
            BigDecimal discountedPrice = discountedPrices.get(orderItem.getItemId());
            orderItem.applyDiscount(discountedPrice);
        }
    }

    public Map<UUID, BigDecimal> getOriginalPrices() {
        return orderItems.stream().collect(Collectors.toMap(OrderItem::getItemId, OrderItem::getTotalOriginalPrice));
    }

    public boolean equalsToTotalPayPrice(BigDecimal totalPayPrice) {
        return this.getTotalPayPrice().compareTo(totalPayPrice) == 0;
    }

    public void completeOrder() {
        this.status = OrderStatus.ORDER_COMPLETED;
    }
}
