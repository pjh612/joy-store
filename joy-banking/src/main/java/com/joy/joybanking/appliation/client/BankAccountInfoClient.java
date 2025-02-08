package com.joy.joybanking.appliation.client;

import com.joy.joybanking.appliation.client.dto.BankAccount;

public interface BankAccountInfoClient {

    BankAccount getBankAccountInfo(String bankName, String bankAccountNo);
}
