package com.joy.joyui.member.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSignupRequest {
    private String username;
    private String password;
    private String name;
    private Gender gender;

}
