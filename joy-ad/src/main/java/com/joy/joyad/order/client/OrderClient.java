package com.joy.joyad.order.client;

import com.joy.joyad.order.dto.OrderSummaryResponse;
import com.joy.joycommon.api.response.ApiResponse;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

public interface OrderClient {
    ApiResponse<OrderSummaryResponse> getOrderSummary(OAuth2AuthorizedClient authorizedClient);
}
