package com.joy.joypayment.adapter.out.client;

import com.joy.joypayment.application.client.BankingClient;
import com.joy.joypayment.application.client.dto.RegisteredBankAccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BankingClientAdapter implements BankingClient {
    private final RestClient bankingRestClient;

    @Override
    public RegisteredBankAccountResponse getRegisteredBankAccount(UUID memberId) {
        return bankingRestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/banking/account").
                        queryParam("memberId", memberId)
                        .build())
                .retrieve()
                .body(RegisteredBankAccountResponse.class);
    }
}
