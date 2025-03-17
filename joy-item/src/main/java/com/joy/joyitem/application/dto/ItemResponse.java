package com.joy.joyitem.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.joy.joyitem.domain.models.Item;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record ItemResponse(
        UUID id,
        BigDecimal price,
        String title,
        String description,
        UUID sellerId,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul") Instant createdAt,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul") Instant updatedAt,
        String creator,
        String updater
) {
    public static ItemResponse of(Item item) {
        return new ItemResponse(
                item.getId(),
                item.getPrice(),
                item.getTitle(),
                item.getDescription(),
                item.getSellerId(),
                item.getCreatedAt(),
                item.getUpdatedAt(),
                item.getCreator(),
                item.getUpdater()
        );
    }
}