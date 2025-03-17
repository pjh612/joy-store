package com.joy.joyadmin.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Configuration
public class WebClientConfig {
    @Bean("joyApiWebClient")
    WebClient joyApiWebClient(WebClient.Builder builder, @Value("${client.joy-api.url}") String url) {
        return builder.baseUrl(url)
                .build();
    }

    @Bean("orderWebClient")
    WebClient orderWebClient(WebClient.Builder builder, @Value("${client.joy-order.url}") String url) {
        return builder.baseUrl(url)
                .build();
    }

    @Bean("itemWebClient")
    WebClient itemWebClient(WebClient.Builder builder, @Value("${client.joy-item.url}") String url) {
        return builder.baseUrl(url)
                .build();
    }
}
