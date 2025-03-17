package com.joy.joyorder.application.usecase.dto;

import com.joy.joyorder.domain.models.Order;
import com.joy.joyorder.domain.models.OrderStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CreateProvisionalOrderResponse(
        UUID id,
        UUID buyerId,
        OrderStatus status,
        List<OrderItemResponse> orderItems,
        BigDecimal amount
) {
    public static CreateProvisionalOrderResponse of(Order order) {
        List<OrderItemResponse> orderItemResponses = order.getOrderItems()
                .stream()
                .map(it -> new OrderItemResponse(
                        it.getId(),
                        it.getItemId(),
                        it.getUnitPrice(),
                        it.getQuantity(),
                        it.getDiscountAmount()))
                .toList();

        return new CreateProvisionalOrderResponse(
                order.getId(),
                order.getBuyerId(),
                order.getStatus(),
                orderItemResponses,
                order.getTotalPayPrice()
        );
    }
}
