package com.joy.joybanking.appliation.client;

import com.joy.joybanking.appliation.client.dto.FindMemberResponse;

public interface MemberClient {
    FindMemberResponse findByMemberId(String memberId);
}
