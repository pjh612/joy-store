package com.joy.joyadmin.seller.client;

import com.joy.joyadmin.seller.dto.FindSellerResponse;
import com.joy.joyadmin.seller.dto.SellerSignupRequest;
import com.joy.joyadmin.seller.dto.SellerSignupResponse;
import com.joy.joycommon.api.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class SellerWebClient implements SellerClient {
    private final WebClient webClient;

    @Override
    public ApiResponse<SellerSignupResponse> signup(SellerSignupRequest request) {
        return webClient.post()
                .uri("/api/sellers")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<SellerSignupResponse>>() {
                })
                .block();
    }

    @Override
    public ApiResponse<FindSellerResponse> findByUsername(String username) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/sellers")
                        .queryParam("username", username)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<FindSellerResponse>>() {
                })
                .block();

    }

    @Override
    public ApiResponse<FindSellerResponse> findByMemberSequence(Long sequence) {
        return webClient.get()
                .uri("/api/sellers/{sequence}", sequence)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<FindSellerResponse>>() {
                })
                .block();
    }
}
