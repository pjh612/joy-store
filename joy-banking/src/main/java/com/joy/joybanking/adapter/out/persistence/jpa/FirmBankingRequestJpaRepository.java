package com.joy.joybanking.adapter.out.persistence.jpa;

import com.joy.joybanking.adapter.out.persistence.jpa.entity.FirmBankingRequestJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FirmBankingRequestJpaRepository extends JpaRepository<FirmBankingRequestJpaEntity, String> {
}
