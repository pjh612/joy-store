package com.joy.joyapi.item.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Item {
    private UUID id;
    private BigDecimal price;
    private String title;
    private String description;
    private UUID sellerId;

    private Instant createdAt;
    private Instant updatedAt;
    private String creator;
    private String updater;

    public static Item createNew(BigDecimal price, String title, String description, UUID sellerId) {
        return new Item(
                null,
                price,
                title,
                description,
                sellerId,
                Instant.now(),
                Instant.now(),
                sellerId.toString(),
                sellerId.toString()
        );
    }
}
