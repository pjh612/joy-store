package com.joy.joyapi.member.adapters.out.persistence.jpa.converter;

import com.joy.joyapi.member.adapters.out.persistence.jpa.entity.entity.MemberEntity;
import com.joy.joyapi.member.domain.models.Gender;
import com.joy.joyapi.member.domain.models.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MemberEntityConverter {

    public MemberEntity toEntity(Member member) {
        log.info("toEntity id = {}", member.getId());
        return new MemberEntity(member.getId(),
                member.getUsername(),
                member.getPassword(),
                member.getName(),
                member.getGender().name()
        );
    }

    public Member toDomain(MemberEntity memberEntity) {
        return new Member(memberEntity.getId(),
                memberEntity.getUsername(),
                memberEntity.getPassword(),
                memberEntity.getName(),
                Gender.valueOf(memberEntity.getGender())
        );
    }
}
