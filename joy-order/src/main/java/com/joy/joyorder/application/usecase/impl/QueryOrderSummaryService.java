package com.joy.joyorder.application.usecase.impl;

import com.joy.joyorder.application.repository.OrderSummaryRepository;
import com.joy.joyorder.application.usecase.QueryOrderSummaryUseCase;
import com.joy.joyorder.application.usecase.dto.OrderSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QueryOrderSummaryService implements QueryOrderSummaryUseCase {
    private final OrderSummaryRepository orderSummaryRepository;

    @Override
    public OrderSummaryResponse query(UUID sellerId) {
        return orderSummaryRepository.findOrderSummaryBySellerId(sellerId);
    }
}
