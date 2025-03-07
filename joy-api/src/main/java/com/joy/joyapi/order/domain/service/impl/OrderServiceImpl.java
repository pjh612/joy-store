package com.joy.joyapi.order.domain.service.impl;

import com.joy.joyapi.coupon.domain.model.AbstractCoupon;
import com.joy.joyapi.item.domain.models.Item;
import com.joy.joyapi.order.domain.models.Order;
import com.joy.joyapi.order.domain.service.OrderService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    @Override
    public Order order(UUID buyerId, List<Item> orderItems, Map<UUID, Integer> orderItemMap, AbstractCoupon coupon) {
        Order newOrder = Order.createNew(buyerId);

        for (Item orderItem : orderItems) {
            newOrder.addOrderItem(orderItem.getId(), orderItem.getPrice(), orderItemMap.get(orderItem.getId()));
        }

        if (coupon != null) {
            Map<UUID, BigDecimal> originalPrices = newOrder.getOriginalPrices();
            Map<UUID, BigDecimal> couponAppliedPrices = originalPrices.entrySet()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, entry -> coupon.getAppliedPrice(entry.getValue())));
            newOrder.applyCoupon(coupon.getId(), couponAppliedPrices);
        }

        return newOrder;
    }
}
