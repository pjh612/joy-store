package com.joy.joyapi.order.adapters.out.persistence.jpa;

import com.joy.joyapi.order.adapters.out.persistence.jpa.entity.OrderEntity;
import com.joy.joyapi.order.adapters.out.persistence.jpa.entity.OrderItemEntity;
import com.joy.joyapi.order.domain.models.Order;
import com.joy.joyapi.order.domain.models.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderEntityConverter {
    public Order toOrderDomain(OrderEntity orderEntity, List<OrderItemEntity> orderItemEntityList) {
        return new Order(orderEntity.getSeq(),
                orderEntity.getBuyerSequence(),
                orderEntity.getStatus(),
                orderItemEntityList.stream()
                        .map(it -> new OrderItem(it.getSeq(), it.getItemSeq(), it.getUnitPrice(), it.getQuantity(), it.getDiscountAmount(), it.getCreatedAt(), it.getUpdatedAt(), it.getCreator(), it.getUpdater()))
                        .toList(),
                orderEntity.getCouponSequence(),
                orderEntity.getCreatedAt(),
                orderEntity.getUpdatedAt(),
                orderEntity.getCreator(),
                orderEntity.getUpdater());
    }
}
