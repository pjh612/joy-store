package com.joy.joyapi.auth.member.application.dto;

public record MemberAuthRequest(
        String username,
        String password
) {
}
