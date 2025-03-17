package com.joy.joyorder.application.repository;

import com.joy.joyorder.application.usecase.dto.OrderSummaryResponse;

import java.util.UUID;

public interface OrderSummaryRepository {
    OrderSummaryResponse findOrderSummaryBySellerId(UUID sellerId);
}
