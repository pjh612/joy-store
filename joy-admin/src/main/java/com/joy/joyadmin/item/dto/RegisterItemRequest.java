package com.joy.joyadmin.item.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record RegisterItemRequest(
        String title,
        String description,
        UUID sellerId,
        BigDecimal price) {
}
