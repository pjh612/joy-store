package com.joy.joyapi.member.adapters.out.persistence.jpa;

import com.joy.joyapi.member.adapters.out.persistence.jpa.entity.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, String> {
    Optional<MemberEntity> findByUsername(String username);
}
