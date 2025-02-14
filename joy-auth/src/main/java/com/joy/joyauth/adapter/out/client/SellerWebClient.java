package com.joy.joyauth.adapter.out.client;

import com.joy.joyauth.application.client.SellerClient;
import com.joy.joyauth.application.client.dto.SellerAuthRequest;
import com.joy.joyauth.application.client.dto.SellerAuthResponse;
import com.joy.joycommon.api.response.ApiResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class SellerWebClient implements SellerClient {
    private final WebClient webClient;

    public SellerWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public ApiResponse<SellerAuthResponse> auth(String id, String password) {
        return webClient.post()
                .uri("/api/seller/authentication")
                .bodyValue(new SellerAuthRequest(id, password))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<SellerAuthResponse>>() {
                })
                .block();
    }
}
