package com.joy.joyapi.member.application;

import com.joy.joyapi.member.application.dto.QueryMemberResponse;

public interface QueryMemberUseCase {
    QueryMemberResponse queryByUsername(String username);

    QueryMemberResponse queryById(String id);
}
