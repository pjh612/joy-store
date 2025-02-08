package com.joy.joybanking.adapter.out.client.member;

import com.joy.joybanking.appliation.client.MemberClient;
import com.joy.joybanking.appliation.client.dto.FindMemberResponse;
import com.joy.joycommon.api.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class MemberWebClientAdapter implements MemberClient {
    private final WebClient memberWebClient;

    @Override
    public FindMemberResponse findByMemberId(String memberId) {
        try {
            return memberWebClient.get()
                    .uri("/api/members/{id}", memberId)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<ApiResponse<FindMemberResponse>>() {
                    })
                    .block()
                    .getData();
        } catch(Exception e){
            return null;
        }
    }
}
