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
    public Order order(Long buyerSeq, List<Item> orderItems, Map<Long, Integer> orderItemMap, AbstractCoupon coupon) {
        Order newOrder = Order.createNew(buyerSeq);

        for (Item orderItem : orderItems) {
            newOrder.addOrderItem(orderItem.getSeq(), orderItem.getPrice(), orderItemMap.get(orderItem.getSeq()));
        }

        if (coupon != null) {
            Map<Long, BigDecimal> originalPrices = newOrder.getOriginalPrices();
            Map<Long, BigDecimal> couponAppliedPrices = originalPrices.entrySet()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, entry -> coupon.use(entry.getValue())));
            newOrder.applyCoupon(coupon.getSequence(), couponAppliedPrices);
        }

        return newOrder;
    }
}
