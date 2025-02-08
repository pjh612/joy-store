package com.joy.joyorchestrator.adapter.out.persistence.jpa;

import com.joy.joyorchestrator.adapter.out.persistence.jpa.entity.SagaStateJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SagaStateJpaRepository extends JpaRepository<SagaStateJpaEntity, UUID> {
}
