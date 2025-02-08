package com.joy.joyapi.order.adapters.out.persistence.jpa;

import com.joy.joyapi.order.adapters.out.persistence.jpa.entity.OrderEntity;
import com.joy.joyapi.order.adapters.out.persistence.jpa.entity.OrderItemEntity;
import com.joy.joyapi.order.domain.models.Order;
import com.joy.joyapi.order.domain.models.OrderItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderEntityConverter {
    public Order toOrderDomain(OrderEntity orderEntity, List<OrderItemEntity> orderItemEntityList) {
        return new Order(orderEntity.getId(),
                orderEntity.getBuyerId(),
                orderEntity.getStatus(),
                orderItemEntityList.stream()
                        .map(it -> new OrderItem(it.getId(), it.getItemId(), it.getUnitPrice(), it.getQuantity(), it.getDiscountAmount(), it.getCreatedAt(), it.getUpdatedAt(), it.getCreator(), it.getUpdater()))
                        .toList(),
                orderEntity.getCouponId(),
                orderEntity.getCreatedAt(),
                orderEntity.getUpdatedAt(),
                orderEntity.getCreator(),
                orderEntity.getUpdater());
    }

    public OrderEntity toOrderEntity(Order order) {
        OrderEntity orderEntity = new OrderEntity(
                order.getId(),
                order.getBuyerId(),
                order.getCouponId(),
                order.getStatus(),
                new ArrayList<>(),
                order.getCreatedAt(),
                order.getUpdatedAt(),
                order.getCreator(),
                order.getUpdater()
        );
        List<OrderItemEntity> orderItemEntities = toOrderItemEntity(orderEntity, order.getOrderItems());
        orderEntity.addOrderItem(orderItemEntities);

        return orderEntity;
    }

    public List<OrderItemEntity> toOrderItemEntity(OrderEntity order, List<OrderItem> orderItemList) {
        return orderItemList.stream()
                .map(it-> toOrderEntity(order, it))
                .toList();
    }

    public OrderItemEntity toOrderEntity(OrderEntity order, OrderItem orderItem) {
        return new OrderItemEntity(orderItem.getId(),
                orderItem.getItemId(),
                order,
                orderItem.getUnitPrice(),
                orderItem.getQuantity(),
                orderItem.getDiscountAmount(),
                orderItem.getCreatedAt(),
                orderItem.getUpdatedAt(),
                orderItem.getCreator(),
                orderItem.getUpdater()
        );
    }
}
