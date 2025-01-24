package com.joy.joyui.order.dto;

import java.util.List;

public record PlaceOrderRequest(List<OrderItemRequestDto> orderItems, Long couponSequence, Long buyerSeq) {
    public record OrderItemRequestDto(Long itemSeq, Integer quantity) {
    }
}
