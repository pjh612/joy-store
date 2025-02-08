package com.joy.joybanking.adapter.out.persistence.jpa;

import com.joy.joybanking.adapter.out.persistence.jpa.entity.RegisteredAccountJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisteredAccountJpaRepository extends JpaRepository<RegisteredAccountJpaEntity, String> {
    Optional<RegisteredAccountJpaEntity> findByMemberId(String memberId);
}
