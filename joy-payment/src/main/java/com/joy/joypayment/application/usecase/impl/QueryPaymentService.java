package com.joy.joypayment.application.usecase.impl;

import com.joy.joypayment.application.dto.PaymentResponse;
import com.joy.joypayment.application.usecase.QueryPaymentUseCase;
import com.joy.joypayment.domain.repository.PaymentRequestRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QueryPaymentService implements QueryPaymentUseCase {
    private final PaymentRequestRepository repository;

    @Override
    public PaymentResponse queryById(UUID requestId) {
        return repository.findById(requestId)
                .map(PaymentResponse::of)
                .orElseThrow(()-> new EntityNotFoundException("주문 정보를 찾을 수 없습니다."));
    }
}
