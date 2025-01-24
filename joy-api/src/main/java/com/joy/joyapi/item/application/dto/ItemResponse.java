package com.joy.joyapi.item.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.joy.joyapi.item.domain.models.Item;

import java.math.BigDecimal;
import java.time.Instant;

public record ItemResponse(
        Long sequence,
        BigDecimal price,
        String title,
        String description,
        Long sellerSeq,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul") Instant createdAt,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul") Instant updatedAt,
        String creator,
        String updater
) {
    public static ItemResponse of(Item item) {
        return new ItemResponse(
                item.getSeq(),
                item.getPrice(),
                item.getTitle(),
                item.getDescription(),
                item.getSellerSeq(),
                item.getCreatedAt(),
                item.getUpdatedAt(),
                item.getCreator(),
                item.getUpdater()
        );
    }
}