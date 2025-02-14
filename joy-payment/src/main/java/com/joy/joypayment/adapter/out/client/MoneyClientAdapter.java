package com.joy.joypayment.adapter.out.client;

import com.joy.joypayment.application.client.MoneyClient;
import com.joy.joypayment.application.client.dto.MoneyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import static org.springframework.security.oauth2.client.web.client.RequestAttributeClientRegistrationIdResolver.clientRegistrationId;


@Component
@RequiredArgsConstructor
public class MoneyClientAdapter implements MoneyClient {
    private final RestClient moneyRestClient;

    @Override
    public MoneyResponse getMemberById() {
        return moneyRestClient.get()
                .uri("/api/external/money")
                .attributes(clientRegistrationId("joy-payment-authorization-code"))
                .retrieve()
                .body(MoneyResponse.class);
    }
}
