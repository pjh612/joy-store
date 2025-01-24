package com.joy.joyui.member.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindMemberResponse {
    private Long seq;
    private String id;
    private String password;
    private String name;
    private Gender gender;
}
