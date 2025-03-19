package com.joy.joyorder.domain.models;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

class OrderTest {

    @Test
    void getTotalPayPriceTest() {
        Order order = Order.createNew(UUID.randomUUID());
        order.addOrderItem(UUID.randomUUID(), "itemName", UUID.randomUUID(), UUID.randomUUID(), BigDecimal.valueOf(1000), 2, BigDecimal.valueOf(1000));

        BigDecimal totalPayPrice = order.getTotalPayPrice();

        Assertions.assertThat(totalPayPrice).isEqualTo(BigDecimal.valueOf(1000));
    }

    @Test
    void completeOrderTest() {
        Order order = Order.createNew(UUID.randomUUID());
        order.addOrderItem(UUID.randomUUID(), "itemName", UUID.randomUUID(), UUID.randomUUID(), BigDecimal.valueOf(1000), 2, BigDecimal.valueOf(1000));

        order.completeOrder();

        Assertions.assertThat(order.getStatus()).isEqualTo(OrderStatus.ORDER_COMPLETED);
    }

    @Test
    void equalsTotalPayPriceTest() {
        Order order = Order.createNew(UUID.randomUUID());
        order.addOrderItem(UUID.randomUUID(), "itemName", UUID.randomUUID(), UUID.randomUUID(), BigDecimal.valueOf(1000), 2, BigDecimal.valueOf(1000));

        Assertions.assertThat(order.equalsToTotalPayPrice(BigDecimal.valueOf(1000))).isTrue();
    }
}