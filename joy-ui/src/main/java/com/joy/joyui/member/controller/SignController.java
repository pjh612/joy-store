package com.joy.joyui.member.controller;

import com.joy.joycommon.api.response.ApiResponse;
import com.joy.joyui.member.client.MemberClient;
import com.joy.joyui.member.dto.MemberSignupRequest;
import com.joy.joyui.member.dto.MemberSignupResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignController {
    private final MemberClient memberClient;

    public SignController(MemberClient memberClient) {
        this.memberClient = memberClient;
    }

    @PostMapping("/api/sign-up")
    public ApiResponse<MemberSignupResponse> signup(@RequestBody MemberSignupRequest request) {
        return memberClient.signup(request);
    }
}
