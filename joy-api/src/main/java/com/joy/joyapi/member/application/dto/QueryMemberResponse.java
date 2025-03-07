package com.joy.joyapi.member.application.dto;

import com.joy.joyapi.member.domain.models.Gender;

import java.util.UUID;

public record QueryMemberResponse(
        UUID id,
        String username,
        String password,
        String name,
        Gender gender
) {
}
