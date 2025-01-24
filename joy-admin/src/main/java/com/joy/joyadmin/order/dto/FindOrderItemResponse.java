package com.joy.joyadmin.order.dto;

import com.joy.joyadmin.item.dto.ItemResponse;

import java.math.BigDecimal;

public record FindOrderItemResponse(
        Long seq,
        ItemResponse item,
        BigDecimal unitPrice,
        Integer quantity,
        BigDecimal discountAmount) {
}
