package com.joy.joyapi.item.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@AllArgsConstructor
public class Item {
    private Long seq;
    private BigDecimal price;
    private String title;
    private String description;
    private Long sellerSeq;

    private Instant createdAt;
    private Instant updatedAt;
    private String creator;
    private String updater;

    public static Item createNew(BigDecimal price, String title, String description, Long sellerSeq) {
        return new Item(
                null,
                price,
                title,
                description,
                sellerSeq,
                Instant.now(),
                Instant.now(),
                sellerSeq.toString(),
                sellerSeq.toString()
        );
    }
}
