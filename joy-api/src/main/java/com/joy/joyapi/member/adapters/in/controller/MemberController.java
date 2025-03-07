package com.joy.joyapi.member.adapters.in.controller;

import com.joy.joyapi.member.application.MemberSignupUseCase;
import com.joy.joyapi.member.application.QueryMemberUseCase;
import com.joy.joyapi.member.application.dto.MemberSignupRequest;
import com.joy.joyapi.member.application.dto.MemberSignupResponse;
import com.joy.joyapi.member.application.dto.QueryMemberResponse;
import com.joy.joycommon.api.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberSignupUseCase memberSignupUsecase;
    private final QueryMemberUseCase queryMemberUsecase;

    public MemberController(MemberSignupUseCase memberSignupUsecase, QueryMemberUseCase queryMemberUsecase) {
        this.memberSignupUsecase = memberSignupUsecase;
        this.queryMemberUsecase = queryMemberUsecase;
    }

    @PostMapping
    public ApiResponse<MemberSignupResponse> signup(@RequestBody MemberSignupRequest request) {
        return new ApiResponse<>(memberSignupUsecase.signup(request));
    }

    @GetMapping
    public ApiResponse<QueryMemberResponse> findByUsername(@RequestParam String username) {
        return new ApiResponse<>(queryMemberUsecase.queryByUsername(username));
    }

    @GetMapping("/{id}")
    public ApiResponse<QueryMemberResponse> findByMemberId(@PathVariable UUID id) {
        return new ApiResponse<>(queryMemberUsecase.queryById(id));
    }

}
