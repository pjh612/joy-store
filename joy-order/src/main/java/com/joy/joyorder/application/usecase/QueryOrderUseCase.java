package com.joy.joyorder.application.usecase;

import com.joy.joyorder.application.usecase.criteria.QueryOrderBySellerIdCriteria;
import com.joy.joyorder.application.usecase.criteria.QueryOrderCriteria;
import com.joy.joyorder.application.usecase.dto.FindOrderResponse;

import java.util.List;
import java.util.UUID;

public interface QueryOrderUseCase {
    List<FindOrderResponse> queryByCriteria(QueryOrderCriteria criteria);

    List<FindOrderResponse> queryBySellerId(QueryOrderBySellerIdCriteria criteria);

    FindOrderResponse queryByOrderId(UUID orderId);
}
