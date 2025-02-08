package com.joy.joyapi.member.application.dto;

import com.joy.joyapi.member.domain.models.Gender;

public record QueryMemberResponse(
        String id,
        String username,
        String password,
        String name,
        Gender gender
) {
}
