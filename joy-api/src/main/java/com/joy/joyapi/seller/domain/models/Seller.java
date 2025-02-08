package com.joy.joyapi.seller.domain.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seller {
    private String id;
    private String username;
    private String password;
    private String name;
    private Gender gender;

    public static Seller creatNew(String id, String password, String name, Gender gender) {
        return new Seller(null, id, password, name, gender);
    }
}
