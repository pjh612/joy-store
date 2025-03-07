package com.joy.joyui.auth.dto;

import java.util.UUID;

public record MemberAuthResponse(
        UUID memberId,
        String name
) {
}
