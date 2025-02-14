package com.joy.joyauth.application.service;

import com.joy.joyauth.application.client.MemberClient;
import com.joy.joyauth.application.client.dto.MemberAuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberClient memberClient;

    public MemberAuthResponse auth(String id, String password) {
        return memberClient.auth(id, password).getData();
    }
}
