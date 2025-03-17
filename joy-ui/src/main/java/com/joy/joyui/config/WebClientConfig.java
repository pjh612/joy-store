package com.joy.joyui.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Value("${harupay.apiKey}")
    private String apiKey;

    @Value("${harupay.clientId}")
    private String clientId;

    @Bean
    WebClient webClient(WebClient.Builder builder, @Value("${client.joy-api.url}") String url) {
        return builder.
                baseUrl(url)
                .build();
    }

    @Bean("paymentClient")
    WebClient paymentClient(WebClient.Builder builder, @Value("${client.payment.url}") String url) {
        return builder.
                baseUrl(url)
                .defaultHeaders(it -> {
                    it.add("Authorization", apiKey);
                    it.add("X-PAY-CLIENT-ID", clientId);
                })
                .build();
    }

    @Bean("orderClient")
    WebClient orderClient(WebClient.Builder builder, @Value("${client.joy-order.url}") String url) {
        return builder.
                baseUrl(url)
                .build();
    }

    @Bean("itemClient")
    WebClient itemClient(WebClient.Builder builder, @Value("${client.joy-item.url}") String url) {
        return builder.
                baseUrl(url)
                .build();
    }
}
