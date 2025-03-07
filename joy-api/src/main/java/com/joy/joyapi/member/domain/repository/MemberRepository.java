package com.joy.joyapi.member.domain.repository;

import com.joy.joyapi.member.domain.models.Member;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findById(UUID id);

    Optional<Member> findByUsername(String username);
}
