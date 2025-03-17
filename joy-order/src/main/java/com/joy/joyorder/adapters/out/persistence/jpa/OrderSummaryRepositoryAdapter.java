package com.joy.joyorder.adapters.out.persistence.jpa;

import com.joy.joyorder.application.repository.OrderSummaryRepository;
import com.joy.joyorder.application.usecase.dto.OrderSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderSummaryRepositoryAdapter implements OrderSummaryRepository {

    private final OrderQuerydslRepository orderQuerydslRepository;

    @Override
    public OrderSummaryResponse findOrderSummaryBySellerId(UUID sellerId) {
        return orderQuerydslRepository.findOrderSummaryBySellerId(sellerId);
    }
}
