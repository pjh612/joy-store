package com.joy.joyapi.item.adapters.out.persistence.jpa.entity;

import com.joy.joycommon.hibernate.annotations.UuidV7Generator;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Table(name = "item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ItemEntity {
    @Id
    @UuidV7Generator
    private UUID id;
    private BigDecimal price;
    private String title;
    private String description;
    private UUID sellerId;
    private Instant createdAt;
    private Instant updatedAt;
    private String creator;
    private String updater;
}
