package com.joy.joyorder.application.client.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemResponse(
        UUID id,
        BigDecimal price,
        String title,
        String description,
        UUID sellerId
) {
}
