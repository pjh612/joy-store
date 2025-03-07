package com.joy.joyapi.member.adapters.out.persistence.jpa.entity.entity;

import com.joy.joycommon.hibernate.annotations.UuidV7Generator;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@ToString
@Entity
@Getter
@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity {
    @Id
    @UuidV7Generator
    private UUID id;
    private String username;
    private String password;
    private String name;
    private String gender;
}
