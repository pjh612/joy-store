package com.joy.joyui.order.dto;

import java.util.List;
import java.util.UUID;

public record CreateProvisionalOrderCommand(
        List<OrderItemRequestDto> orderItems,
        UUID buyerId,
        String payType
) {
    public record OrderItemRequestDto(
            String itemId,
            String couponId,
            Integer quantity
    ) {
    }
}
