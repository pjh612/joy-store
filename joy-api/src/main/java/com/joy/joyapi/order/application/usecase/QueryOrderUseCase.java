package com.joy.joyapi.order.application.usecase;

import com.joy.joyapi.order.application.usecase.criteria.QueryOrderCriteria;
import com.joy.joyapi.order.application.usecase.dto.FindOrderResponse;

import java.util.List;
import java.util.UUID;

public interface QueryOrderUseCase {
    List<FindOrderResponse> queryByCriteria(QueryOrderCriteria criteria);

    List<FindOrderResponse> queryBySellerId(UUID sellerId);

    FindOrderResponse queryByOrderId(UUID orderId);
}
