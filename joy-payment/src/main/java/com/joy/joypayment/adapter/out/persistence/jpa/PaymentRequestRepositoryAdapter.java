package com.joy.joypayment.adapter.out.persistence.jpa;

import com.joy.joypayment.adapter.out.persistence.jpa.entity.PaymentRequestJpaEntity;
import com.joy.joypayment.domain.model.PaymentRequest;
import com.joy.joypayment.domain.repository.PaymentRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PaymentRequestRepositoryAdapter implements PaymentRequestRepository {
    private final PaymentRequestJpaRepository repository;

    @Override
    public PaymentRequest save(PaymentRequest paymentRequest) {
        PaymentRequestJpaEntity entity = PaymentRequestConverter.toEntity(paymentRequest);
        return PaymentRequestConverter.toDomain(repository.save(entity));
    }

    @Override
    public Optional<PaymentRequest> findById(UUID paymentRequestId) {
        return repository.findById(paymentRequestId)
                .map(PaymentRequestConverter::toDomain);
    }
}
