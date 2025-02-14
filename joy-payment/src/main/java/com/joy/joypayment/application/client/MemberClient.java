package com.joy.joypayment.application.client;

import com.joy.joypayment.application.client.dto.MemberResponse;

import java.util.UUID;

public interface MemberClient {
    MemberResponse getMemberById(UUID memberId);
}
