package com.joy.joyapi.member.application;

import com.joy.joyapi.member.application.dto.QueryMemberResponse;

import java.util.UUID;

public interface QueryMemberUseCase {
    QueryMemberResponse queryByUsername(String username);

    QueryMemberResponse queryById(UUID id);
}
