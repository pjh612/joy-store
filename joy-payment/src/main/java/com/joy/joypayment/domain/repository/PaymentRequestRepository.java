package com.joy.joypayment.domain.repository;

import com.joy.joypayment.domain.model.PaymentRequest;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRequestRepository {
    PaymentRequest save(PaymentRequest paymentRequest);

    Optional<PaymentRequest> findById(UUID paymentRequestId);
}
