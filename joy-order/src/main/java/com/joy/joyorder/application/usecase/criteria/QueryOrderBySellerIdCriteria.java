package com.joy.joyorder.application.usecase.criteria;

import java.util.UUID;

public record QueryOrderBySellerIdCriteria(
        UUID sellerId,
        UUID lastId,
        String sort,
        String direction,
        long size
) {

}
