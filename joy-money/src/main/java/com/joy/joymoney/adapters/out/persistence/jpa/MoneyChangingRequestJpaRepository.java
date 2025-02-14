package com.joy.joymoney.adapters.out.persistence.jpa;

import com.joy.joymoney.adapters.out.persistence.jpa.entity.MoneyChangingRequestJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MoneyChangingRequestJpaRepository extends JpaRepository<MoneyChangingRequestJpaEntity, UUID> {
}
