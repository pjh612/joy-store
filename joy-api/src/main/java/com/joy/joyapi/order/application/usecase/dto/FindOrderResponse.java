package com.joy.joyapi.order.application.usecase.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.joy.joyapi.item.application.dto.ItemResponse;
import com.joy.joyapi.order.domain.models.Order;
import com.joy.joyapi.order.domain.models.OrderStatus;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public record FindOrderResponse(
        UUID id,
        UUID buyerId,
        OrderStatus status,
        List<FindOrderItemResponse> orderItems,
        UUID couponId,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        Instant createdAt
) {

    public static FindOrderResponse of(Order order, Map<UUID, ItemResponse> itemResponseMap) {
        return new FindOrderResponse(order.getId(),
                order.getBuyerId(),
                order.getStatus(),
                order.getOrderItems()
                        .stream()
                        .map(it -> FindOrderItemResponse.of(it, itemResponseMap.get(it.getItemId())))
                        .toList(),
                order.getCouponId(),
                order.getCreatedAt());
    }
}
