package com.joy.joyapi.auth.member.application.dto;

import java.util.UUID;

public record MemberAuthResponse(
        UUID memberId,
        String name
) {
}
