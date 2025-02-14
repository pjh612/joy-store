package com.joy.joyauth.adapter.out.client;

import com.joy.joyauth.application.client.MemberClient;
import com.joy.joyauth.application.client.dto.MemberAuthRequest;
import com.joy.joyauth.application.client.dto.MemberAuthResponse;
import com.joy.joycommon.api.response.ApiResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class MemberWebClient implements MemberClient {
    private final WebClient webClient;

    public MemberWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public ApiResponse<MemberAuthResponse> auth(String id, String password) {
        return webClient.post()
                .uri("/api/member/authentication")
                .bodyValue(new MemberAuthRequest(id, password))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<MemberAuthResponse>>() {
                })
                .block();
    }
}
