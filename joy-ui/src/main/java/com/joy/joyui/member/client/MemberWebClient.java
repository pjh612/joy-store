package com.joy.joyui.member.client;

import com.joy.joycommon.api.response.ApiResponse;
import com.joy.joyui.member.dto.FindMemberResponse;
import com.joy.joyui.member.dto.MemberSignupRequest;
import com.joy.joyui.member.dto.MemberSignupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class MemberWebClient implements MemberClient {
    private final WebClient webClient;

    @Override
    public ApiResponse<MemberSignupResponse> signup(MemberSignupRequest request) {
        return webClient.post()
                .uri("/api/members")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<MemberSignupResponse>>() {
                })
                .block();
    }

    @Override
    public ApiResponse<FindMemberResponse> findByUsername(String username) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/members")
                        .queryParam("username", username)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<FindMemberResponse>>() {
                })
                .block();
    }

    @Override
    public ApiResponse<FindMemberResponse> findByMemberId(String memberId) {
        return webClient.get()
                .uri("/api/members/{id}", memberId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<FindMemberResponse>>() {
                })
                .block();
    }
}
