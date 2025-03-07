package com.joy.joyapi.member.application.impl;

import com.joy.joyapi.member.application.QueryMemberUseCase;
import com.joy.joyapi.member.application.dto.QueryMemberResponse;
import com.joy.joyapi.member.domain.models.Member;
import com.joy.joyapi.member.domain.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class QueryMemberService implements QueryMemberUseCase {
    private final MemberRepository memberRepository;

    public QueryMemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public QueryMemberResponse queryByUsername(String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(EntityNotFoundException::new);

        return new QueryMemberResponse(member.getId(), member.getUsername(), member.getPassword(), member.getName(), member.getGender());
    }

    public QueryMemberResponse queryById(UUID id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return new QueryMemberResponse(member.getId(), member.getUsername(), member.getPassword(), member.getName(), member.getGender());
    }
}
