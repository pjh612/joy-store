package com.joy.joyapi.order.application.usecase;

import com.joy.joyapi.order.application.usecase.dto.FindOrderResponse;

import java.util.List;
import java.util.UUID;

public interface QueryOrderUseCase {
    List<FindOrderResponse> queryByMemberId(UUID memberId);

    List<FindOrderResponse> queryBySellerId(UUID sellerId);
    
}
