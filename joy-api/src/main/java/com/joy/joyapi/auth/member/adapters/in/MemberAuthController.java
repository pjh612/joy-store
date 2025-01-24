package com.joy.joyapi.auth.member.adapters.in;

import com.joy.joyapi.auth.member.application.dto.MemberAuthRequest;
import com.joy.joyapi.auth.member.application.dto.MemberAuthResponse;
import com.joy.joyapi.auth.member.application.usecase.MemberAuthUseCase;
import com.joy.joycommon.api.response.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member/authentication")
public class MemberAuthController {
    private final MemberAuthUseCase memberAuthUsecase;

    public MemberAuthController(MemberAuthUseCase memberAuthUsecase) {
        this.memberAuthUsecase = memberAuthUsecase;
    }

    @PostMapping
    public ApiResponse<MemberAuthResponse> authenticate(@RequestBody MemberAuthRequest request) {
        return ApiResponse.of(memberAuthUsecase.authenticate(request));
    }
}
