package com.joy.joyorder.application.usecase.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.joy.joyorder.application.client.dto.ItemResponse;
import com.joy.joyorder.domain.models.Order;
import com.joy.joyorder.domain.models.OrderStatus;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public record FindOrderResponse(
        UUID id,
        UUID buyerId,
        OrderStatus status,
        List<FindOrderItemResponse> orderItems,
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
                order.getCreatedAt());
    }
}
