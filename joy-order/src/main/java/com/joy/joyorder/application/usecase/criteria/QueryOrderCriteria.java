package com.joy.joyorder.application.usecase.criteria;

import com.joy.joyorder.domain.models.OrderStatus;

import java.util.Collection;
import java.util.UUID;

public record QueryOrderCriteria(
        UUID buyerId,
        UUID sellerId,
        Collection<OrderStatus> excludeStatus,
        String cursorType,
        UUID lastId,
        String sort,
        String direction,
        long size
) {

}
