package com.joy.joyorder.application.usecase;

import com.joy.joyorder.application.usecase.dto.OrderSummaryResponse;

import java.util.UUID;

public interface QueryOrderSummaryUseCase {
    OrderSummaryResponse query(UUID sellerId);
}
