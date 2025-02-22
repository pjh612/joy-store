package com.joy.joyadmin.order.dto;

import com.joy.joyadmin.item.dto.ItemResponse;

import java.math.BigDecimal;

public record FindOrderItemResponse(
        String id,
        ItemResponse item,
        BigDecimal unitPrice,
        Integer quantity,
        BigDecimal discountAmount) {
}
