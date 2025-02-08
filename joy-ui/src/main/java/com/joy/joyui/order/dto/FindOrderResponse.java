package com.joy.joyui.order.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;
import java.util.List;

public record FindOrderResponse(String id, String buyerId, String status, List<FindOrderItemResponse> orderItems,
                                String couponId,
                                @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul") Instant createdAt) {
}
