package com.joy.joyui.member.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindMemberResponse {
    private String id;
    private String username;
    private String password;
    private String name;
    private Gender gender;
}
