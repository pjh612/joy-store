package com.joy.joyapi.order.application.usecase;

import com.joy.joyapi.order.application.usecase.dto.OrderSummaryResponse;

public interface QueryOrderSummaryUseCase {
    OrderSummaryResponse query(Long sellerSequence);
}
