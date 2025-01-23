package com.joy.joyapi.member.domain.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    private Long seq;
    private String id;
    private String password;
    private String name;
    private Gender gender;

    public static Member creatNew(String id, String password, String name, Gender gender) {
        return new Member(null, id, password, name, gender);
    }
}
