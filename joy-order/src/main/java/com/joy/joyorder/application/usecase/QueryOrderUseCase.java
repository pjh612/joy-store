package com.joy.joyorder.application.usecase;

import com.joy.joyorder.application.usecase.dto.QueryOrderBySellerIdRequest;
import com.joy.joyorder.application.usecase.dto.QueryOrderRequest;
import com.joy.joyorder.domain.repository.criteria.QueryOrderBySellerIdCriteria;
import com.joy.joyorder.domain.repository.criteria.QueryOrderCriteria;
import com.joy.joyorder.application.usecase.dto.FindOrderResponse;

import java.util.List;
import java.util.UUID;

public interface QueryOrderUseCase {
    List<FindOrderResponse> queryByCriteria(QueryOrderRequest request);

    List<FindOrderResponse> queryBySellerId(QueryOrderBySellerIdRequest request);

    FindOrderResponse queryByOrderId(UUID orderId);
}
