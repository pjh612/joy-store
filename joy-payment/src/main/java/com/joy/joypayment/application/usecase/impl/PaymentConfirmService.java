package com.joy.joypayment.application.usecase.impl;

import com.joy.joypayment.application.dto.CompletePaymentRequest;
import com.joy.joypayment.application.usecase.PaymentConfirmUseCase;
import com.joy.joypayment.domain.model.PaymentRequest;
import com.joy.joypayment.domain.repository.PaymentRequestRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentConfirmService implements PaymentConfirmUseCase {
    private final PaymentRequestRepository repository;

    @Override
    @Transactional
    public void confirm(CompletePaymentRequest request) {
        PaymentRequest foundRequest = repository.findById(request.requestId())
                .orElseThrow(() -> new EntityNotFoundException("결제 요청을 찾을 수 없습니다."));
        foundRequest.success();

        repository.save(foundRequest);
    }

    @Override
    @Transactional
    public void failConfirm(UUID requestId) {
        PaymentRequest paymentRequest = repository.findById(requestId)
                .orElse(null);

        if (paymentRequest == null) {
            return;
        }

        paymentRequest.fail();

        repository.save(paymentRequest);

    }
}
