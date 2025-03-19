package com.joy.joyorder.domain.repository.criteria;

import com.joy.joyorder.domain.models.OrderStatus;

import java.util.Collection;
import java.util.UUID;

public record QueryOrderBySellerIdCriteria(
        UUID sellerId,
        Collection<OrderStatus> excludeStatus,
        UUID lastId,
        String sort,
        String direction,
        long size
) {
}
