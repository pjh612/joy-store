package com.joy.joyui.auth.client;

import com.joy.joycommon.api.response.ApiResponse;
import com.joy.joyui.auth.dto.MemberAuthRequest;
import com.joy.joyui.auth.dto.MemberAuthResponse;

public interface MemberAuthClient {
    ApiResponse<MemberAuthResponse> authenticate(MemberAuthRequest request);
}
