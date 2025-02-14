package com.joy.joyauth.application.client;

import com.joy.joyauth.application.client.dto.MemberAuthResponse;
import com.joy.joycommon.api.response.ApiResponse;

public interface MemberClient {
    ApiResponse<MemberAuthResponse> auth(String id, String password);
}
