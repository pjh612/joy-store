package com.joy.joyauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {
    @Bean
    WebClient webClient(WebClient.Builder builder) {
        HttpClient httpClient = HttpClient.create();

        return builder
                .baseUrl("http://joy-api:8081")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
