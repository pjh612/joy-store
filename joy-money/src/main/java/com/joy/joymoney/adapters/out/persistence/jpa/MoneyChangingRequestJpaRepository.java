package com.joy.joymoney.adapters.out.persistence.jpa;

import com.joy.joymoney.adapters.out.persistence.jpa.entity.MoneyChangingRequestJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MoneyChangingRequestJpaRepository extends JpaRepository<MoneyChangingRequestJpaEntity, String> {
    Optional<MoneyChangingRequestJpaEntity> findByTargetMemberId(String targetMemberId);
}
