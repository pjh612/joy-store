package com.joy.joyad.order.client;

import com.joy.joyad.order.dto.OrderSummaryResponse;
import com.joy.joycommon.api.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@Component
@RequiredArgsConstructor
public class OrderClientImpl implements OrderClient {
    private final WebClient webClient;

    @Override
    public ApiResponse<OrderSummaryResponse> getOrderSummary(OAuth2AuthorizedClient authorizedClient) {
        return this.webClient.get()
                .uri("http://joy-order:8084/api/external/orders/summary")
                .attributes(oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<OrderSummaryResponse>>() {
                })
                .block();
    }
}
