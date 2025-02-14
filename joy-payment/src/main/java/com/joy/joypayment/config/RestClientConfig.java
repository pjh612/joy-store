package com.joy.joypayment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.client.OAuth2ClientHttpRequestInterceptor;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean("memberRestClient")
    public RestClient memberRestClient(RestClient.Builder builder, @Value("${client.member.url}") String url) {
        return builder.baseUrl(url)
                .build();
    }

    @Bean("bankingRestClient")
    public RestClient bankingRestClient(RestClient.Builder builder, @Value("${client.banking.url}") String url) {
        return builder.baseUrl(url)
                .build();
    }

    @Bean("moneyRestClient")
    public RestClient moneyRestClient(RestClient.Builder builder, OAuth2AuthorizedClientManager authorizedClientManager, @Value("${client.money.url}") String url) {
        OAuth2ClientHttpRequestInterceptor requestInterceptor =
                new OAuth2ClientHttpRequestInterceptor(authorizedClientManager);
        return builder.baseUrl(url)
                .requestInterceptor(requestInterceptor)
                .build();
    }
}
