package com.joy.joyadmin.auth.client;

import com.joy.joyadmin.auth.dto.SellerAuthRequest;
import com.joy.joyadmin.auth.dto.SellerAuthResponse;
import com.joy.joycommon.api.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class SellerAuthWebClient implements SellerAuthClient {
    private final WebClient webClient;

    @Override
    public ApiResponse<SellerAuthResponse> authenticate(SellerAuthRequest request) {
        return webClient.post()
                .uri("/api/seller/authentication")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<SellerAuthResponse>>() {
                })
                .block();
    }
}
