package com.joy.joymoney.adapters.out.persistence.jpa;

import com.joy.joymoney.adapters.out.persistence.jpa.entity.MoneyJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MoneyJpaRepository extends JpaRepository<MoneyJpaEntity, String> {
    Optional<MoneyJpaEntity> findByMemberId(String memberId);
}
