package com.joy.joypayment.adapter.out.persistence.jpa;

import com.joy.joypayment.adapter.out.persistence.jpa.entity.PaymentRequestJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRequestJpaRepository extends JpaRepository<PaymentRequestJpaEntity, UUID> {
}
