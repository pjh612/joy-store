package com.joy.joyapi.order.application.usecase;

import com.joy.joyapi.order.application.usecase.dto.OrderSummaryResponse;

import java.util.UUID;

public interface QueryOrderSummaryUseCase {
    OrderSummaryResponse query(UUID sellerId);
}
