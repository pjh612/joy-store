package com.joy.joyad.order.service;

import com.joy.joyad.order.client.OrderClient;
import com.joy.joyad.order.dto.OrderSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderClient orderClient;

    public OrderSummaryResponse getOrderSummary(OAuth2AuthorizedClient authorizedClient) {
        return orderClient.getOrderSummary(authorizedClient).getData();
    }
}
