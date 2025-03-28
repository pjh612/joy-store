package com.joy.joyui.member.client;

import com.joy.joycommon.api.response.ApiResponse;
import com.joy.joyui.member.dto.FindMemberResponse;
import com.joy.joyui.member.dto.MemberSignupRequest;
import com.joy.joyui.member.dto.MemberSignupResponse;

import java.util.UUID;

public interface MemberClient {
    ApiResponse<MemberSignupResponse> signup(MemberSignupRequest request);

    ApiResponse<FindMemberResponse> findByUsername(String username);

    ApiResponse<FindMemberResponse> findByMemberId(UUID memberId);
}
