package com.joy.joyapi.order.application.usecase.criteria;

import com.joy.joyapi.order.domain.models.OrderStatus;

import java.util.Collection;
import java.util.UUID;

public record QueryOrderCriteria(
        UUID buyerId,
        Collection<OrderStatus> excludeStatus
) {

}
