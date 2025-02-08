package com.joy.joybanking.adapter.out.client.bank;


import com.joy.joybanking.appliation.client.BankAccountInfoClient;
import com.joy.joybanking.appliation.client.FirmBankingClient;
import com.joy.joybanking.appliation.client.dto.BankAccount;
import com.joy.joybanking.appliation.client.dto.ExternalFirmBankingRequest;
import com.joy.joybanking.appliation.client.dto.ExternalFirmBankingResponse;
import org.springframework.stereotype.Component;

@Component
public class BankAccountWebClientAdapter implements BankAccountInfoClient, FirmBankingClient {
    @Override
    public BankAccount getBankAccountInfo(String bankName, String bankAccountNo) {
        return new BankAccount(bankName, bankAccountNo, true);
    }

    @Override
    public ExternalFirmBankingResponse requestFirmBanking(ExternalFirmBankingRequest request) {
        return new ExternalFirmBankingResponse(1);
    }
}
