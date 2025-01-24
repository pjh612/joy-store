package com.joy.joyapi.member.adapters.out.persistence.jpa.converter;

import com.joy.joyapi.member.adapters.out.persistence.jpa.entity.entity.MemberEntity;
import com.joy.joyapi.member.domain.models.Gender;
import com.joy.joyapi.member.domain.models.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberEntityConverter {

    public MemberEntity toEntity(Member member) {
        return new MemberEntity(member.getSeq(),
                member.getId(),
                member.getPassword(),
                member.getName(),
                member.getGender().name()
        );
    }

    public Member toDomain(MemberEntity memberEntity) {
        return new Member(memberEntity.getSeq(),
                memberEntity.getUsername(),
                memberEntity.getPassword(),
                memberEntity.getName(),
                Gender.valueOf(memberEntity.getGender())
        );
    }
}
