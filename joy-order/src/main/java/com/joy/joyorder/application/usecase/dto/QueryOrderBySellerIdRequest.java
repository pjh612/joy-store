package com.joy.joyorder.application.usecase.dto;

import com.joy.joyorder.domain.models.OrderStatus;
import com.joy.joyorder.domain.repository.criteria.QueryOrderBySellerIdCriteria;

import java.util.UUID;

public record QueryOrderBySellerIdRequest(
        UUID sellerId,
        UUID lastId,
        String sort,
        String direction,
        long size
) {
    public QueryOrderBySellerIdCriteria toCriteria() {
        return new QueryOrderBySellerIdCriteria(
                this.sellerId,
                OrderStatus.getVisibleStatusesForUser(),
                this.lastId,
                this.sort,
                this.direction,
                this.size
        );
    }
}
