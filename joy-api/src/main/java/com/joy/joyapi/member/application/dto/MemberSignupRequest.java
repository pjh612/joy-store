package com.joy.joyapi.member.application.dto;

import com.joy.joyapi.member.domain.models.Gender;
import com.joy.joyapi.member.domain.models.Member;

public record MemberSignupRequest(
        String username,
        String password,
        String name,
        Gender gender
) {
    public Member toEntity() {
        return Member.creatNew(username, password, name, gender);
    }
}
