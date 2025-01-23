package com.joy.joyapi.order.domain.service;

import com.joy.joyapi.coupon.domain.model.AbstractCoupon;
import com.joy.joyapi.item.domain.models.Item;
import com.joy.joyapi.order.domain.models.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {
    Order order(Long buyerSeq, List<Item> items, Map<Long, Integer> orderItemMap, AbstractCoupon coupon);
}
