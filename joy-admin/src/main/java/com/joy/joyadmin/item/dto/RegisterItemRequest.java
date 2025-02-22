package com.joy.joyadmin.item.dto;

import java.math.BigDecimal;

public record RegisterItemRequest(
        String title,
        String description,
        String sellerId,
        BigDecimal price) {
}
