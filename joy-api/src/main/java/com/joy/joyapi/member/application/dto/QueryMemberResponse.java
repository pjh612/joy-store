package com.joy.joyapi.member.application.dto;

import com.joy.joyapi.member.domain.models.Gender;

public record QueryMemberResponse(
        Long seq,
        String id,
        String password,
        String name,
        Gender gender
) {
}
