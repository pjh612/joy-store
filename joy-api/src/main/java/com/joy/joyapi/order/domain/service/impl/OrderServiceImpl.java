package com.joy.joyapi.order.domain.service.impl;

import com.joy.joyapi.coupon.domain.model.AbstractCoupon;
import com.joy.joyapi.item.domain.models.Item;
import com.joy.joyapi.order.domain.models.Order;
import com.joy.joyapi.order.domain.service.OrderService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    @Override
    public Order order(String buyerId, List<Item> orderItems, Map<String, Integer> orderItemMap, AbstractCoupon coupon) {
        Order newOrder = Order.createNew(buyerId);

        for (Item orderItem : orderItems) {
            newOrder.addOrderItem(orderItem.getId(), orderItem.getPrice(), orderItemMap.get(orderItem.getId()));
        }

        if (coupon != null) {
            Map<String, BigDecimal> originalPrices = newOrder.getOriginalPrices();
            Map<String, BigDecimal> couponAppliedPrices = originalPrices.entrySet()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, entry -> coupon.use(entry.getValue())));
            newOrder.applyCoupon(coupon.getId(), couponAppliedPrices);
        }

        return newOrder;
    }
}
