package com.joy.joyapi.member.application.impl;

import com.joy.joyapi.member.application.QueryMemberUseCase;
import com.joy.joyapi.member.application.dto.QueryMemberResponse;
import com.joy.joyapi.member.domain.models.Member;
import com.joy.joyapi.member.domain.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

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

        return new QueryMemberResponse(member.getSeq(), member.getId(), member.getPassword(), member.getName(), member.getGender());
    }

    @Override
    public QueryMemberResponse queryBySequence(Long sequence) {
        Member member = memberRepository.findBySeq(sequence)
                .orElseThrow(EntityNotFoundException::new);

        return new QueryMemberResponse(member.getSeq(), member.getId(), member.getPassword(), member.getName(), member.getGender());
    }
}
