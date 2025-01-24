package com.joy.joyapi.auth.member.application.usecase;

import com.joy.joyapi.auth.member.application.dto.MemberAuthRequest;
import com.joy.joyapi.auth.member.application.dto.MemberAuthResponse;

public interface MemberAuthUseCase {
    MemberAuthResponse authenticate(MemberAuthRequest request);
}
