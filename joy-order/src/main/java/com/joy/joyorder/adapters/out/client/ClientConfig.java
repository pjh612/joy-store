package com.joy.joyorder.adapters.out.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientConfig {

    @Bean("couponClient")
    public RestClient couponClient(RestClient.Builder builder, @Value("${client.coupon.url}") String url) {
        return builder.baseUrl(url).build();
    }

    @Bean("itemClient")
    public RestClient itemClient(RestClient.Builder builder, @Value("${client.item.url}") String url) {
        return builder.baseUrl(url).build();
    }
}
