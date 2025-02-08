package com.joy.joyui.order.dto;

import com.joy.joyui.item.dto.ItemResponse;

import java.math.BigDecimal;

public record FindOrderItemResponse(String id, ItemResponse item, BigDecimal unitPrice, Integer quantity,
                                    BigDecimal discountAmount) {
}
