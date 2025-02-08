package com.joy.joyapi.order.application.usecase;

import com.joy.joyapi.order.application.usecase.dto.FindOrderResponse;

import java.util.List;

public interface QueryOrderUseCase {
    List<FindOrderResponse> queryByMemberId(String memberId);

    List<FindOrderResponse> queryBySellerId(String sellerId);
}
