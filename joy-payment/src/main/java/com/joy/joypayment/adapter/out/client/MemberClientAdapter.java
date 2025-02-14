package com.joy.joypayment.adapter.out.client;

import com.joy.joypayment.application.client.MemberClient;
import com.joy.joypayment.application.client.dto.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MemberClientAdapter implements MemberClient {
    private final RestClient memberRestClient;
    @Override
    public MemberResponse getMemberById(UUID memberId) {
        return memberRestClient.get()
                .uri("/api/members/{memberId}", memberId)
                .retrieve()
                .body(MemberResponse.class);
    }
}
