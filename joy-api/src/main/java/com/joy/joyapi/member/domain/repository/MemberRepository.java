package com.joy.joyapi.member.domain.repository;

import com.joy.joyapi.member.domain.models.Member;

import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findById(String id);

    Optional<Member> findByUsername(String username);
}
