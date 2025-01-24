package com.joy.joyapi.member.application.impl;

import com.joy.joyapi.member.application.MemberSignupUseCase;
import com.joy.joyapi.member.application.dto.MemberSignupRequest;
import com.joy.joyapi.member.application.dto.MemberSignupResponse;
import com.joy.joyapi.member.domain.models.Member;
import com.joy.joyapi.member.domain.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberSignupService implements MemberSignupUseCase {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberSignupService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public MemberSignupResponse signup(MemberSignupRequest request) {
        Member entity = request.toEntity();
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        return new MemberSignupResponse(memberRepository.save(entity).getId());
    }
}
