package com.joy.joyadmin.item.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.Instant;

public record ItemResponse(
        Long MemberIduence,
        BigDecimal price,
        String title,
        String description,
        Long sellerSeq,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        Instant createdAt,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        Instant updatedAt,
        String creator, String updater) {
}
