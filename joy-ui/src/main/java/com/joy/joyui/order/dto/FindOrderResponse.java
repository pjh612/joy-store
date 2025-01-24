package com.joy.joyui.order.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;
import java.util.List;

public record FindOrderResponse(Long seq, Long buyerSequence, String status, List<FindOrderItemResponse> orderItems,
                                Long couponSeq,
                                @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul") Instant createdAt) {
}
