package com.joy.joyapi.member.application;

import com.joy.joyapi.member.application.dto.MemberSignupRequest;
import com.joy.joyapi.member.application.dto.MemberSignupResponse;

public interface MemberSignupUseCase {
    MemberSignupResponse signup(MemberSignupRequest request);
}
