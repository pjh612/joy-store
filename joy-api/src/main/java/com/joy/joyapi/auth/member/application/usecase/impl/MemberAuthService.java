package com.joy.joyapi.auth.member.application.usecase.impl;

import com.joy.joyapi.auth.member.application.dto.MemberAuthRequest;
import com.joy.joyapi.auth.member.application.dto.MemberAuthResponse;
import com.joy.joyapi.auth.member.application.usecase.MemberAuthUseCase;
import com.joy.joyapi.common.exception.AuthenticationException;
import com.joy.joyapi.member.application.dto.QueryMemberResponse;
import com.joy.joyapi.member.application.impl.QueryMemberService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberAuthService implements MemberAuthUseCase {
    private final QueryMemberService queryMemberService;
    private final PasswordEncoder passwordEncoder;

    public MemberAuthService(QueryMemberService queryMemberService, PasswordEncoder passwordEncoder) {
        this.queryMemberService = queryMemberService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public MemberAuthResponse authenticate(MemberAuthRequest request) {
        try {
            QueryMemberResponse member = queryMemberService.queryByUsername(request.username());

            if (!passwordEncoder.matches(request.password(), member.password())) {
                throw new BadCredentialsException("아이디 비밀번호 확인 필요");
            }

            return new MemberAuthResponse(member.id(), member.name());
        } catch (EntityNotFoundException e) {
            throw new AuthenticationException("아이디 비밀번호 확인 필요");
        }
    }
}
