package com.joy.joyorder.application.usecase.dto;

import com.joy.joyorder.domain.models.OrderStatus;
import com.joy.joyorder.domain.repository.criteria.QueryOrderCriteria;

import java.util.Collection;
import java.util.UUID;

public record QueryOrderRequest(
        UUID buyerId,
        Collection<OrderStatus> excludeStatus,
        String cursorType,
        UUID lastId,
        String sort,
        String direction,
        long size
) {

    public QueryOrderCriteria toCriteria() {
        return new QueryOrderCriteria(
                this.buyerId,
                OrderStatus.getVisibleStatusesForUser(),
                this.cursorType,
                this.lastId,
                this.sort,
                this.direction,
                this.size
        );
    }

}
