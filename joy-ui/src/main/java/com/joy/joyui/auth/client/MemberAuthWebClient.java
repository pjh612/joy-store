package com.joy.joyui.auth.client;

import com.joy.joycommon.api.response.ApiResponse;
import com.joy.joyui.auth.dto.MemberAuthRequest;
import com.joy.joyui.auth.dto.MemberAuthResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class MemberAuthWebClient implements MemberAuthClient {
    private final WebClient webClient;

    public MemberAuthWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public ApiResponse<MemberAuthResponse> authenticate(MemberAuthRequest request) {
        return webClient.post()
                .uri("/api/member/authentication")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<MemberAuthResponse>>() {
                })
                .block();
    }
}
