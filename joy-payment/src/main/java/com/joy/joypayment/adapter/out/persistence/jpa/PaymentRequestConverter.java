package com.joy.joypayment.adapter.out.persistence.jpa;

import com.joy.joypayment.adapter.out.persistence.jpa.entity.PaymentRequestJpaEntity;
import com.joy.joypayment.domain.model.PaymentRequest;

public class PaymentRequestConverter {
    public static PaymentRequest toDomain(PaymentRequestJpaEntity entity) {
        return new PaymentRequest(
                entity.getRequestId(),
                entity.getRequestMemberId(),
                entity.getRequestPrice(),
                entity.getSellerId(),
                entity.getPaymentStatus(),
                entity.getApprovedAt()
        );
    }

    public static PaymentRequestJpaEntity toEntity(PaymentRequest domain) {
        return new PaymentRequestJpaEntity(
                domain.getRequestId(),
                domain.getRequestMemberId(),
                domain.getRequestPrice(),
                domain.getSellerId(),
                domain.getPaymentStatus(),
                domain.getApprovedAt()
        );
    }
}
