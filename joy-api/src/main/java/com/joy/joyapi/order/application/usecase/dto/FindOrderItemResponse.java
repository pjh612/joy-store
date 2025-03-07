package com.joy.joyapi.order.application.usecase.dto;

import com.joy.joyapi.item.application.dto.ItemResponse;
import com.joy.joyapi.order.domain.models.OrderItem;

import java.math.BigDecimal;
import java.util.UUID;

public record FindOrderItemResponse(
        UUID id,
        ItemResponse item,
        BigDecimal unitPrice,
        Integer quantity,
        BigDecimal discountAmount
) {

    public static FindOrderItemResponse of(OrderItem orderItem, ItemResponse itemResponse) {
        return new FindOrderItemResponse(orderItem.getId(),
                itemResponse,
                orderItem.getUnitPrice(),
                orderItem.getQuantity(),
                orderItem.getDiscountAmount());
    }
}
