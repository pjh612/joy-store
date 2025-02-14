package com.joy.joypayment.application.client;

import com.joy.joypayment.application.client.dto.RegisteredBankAccountResponse;

import java.util.UUID;

public interface BankingClient {
    RegisteredBankAccountResponse getRegisteredBankAccount(UUID memberId);
}
