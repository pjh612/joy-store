package com.joy.joyorder.application.usecase.dto;

import com.joy.joyorder.application.client.dto.ItemResponse;
import com.joy.joyorder.domain.models.OrderItem;

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
