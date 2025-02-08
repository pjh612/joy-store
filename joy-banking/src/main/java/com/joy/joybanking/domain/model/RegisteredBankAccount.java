package com.joy.joybanking.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisteredBankAccount {
    private String id;
    private String memberId;
    private String bankName;
    private String accountNumber;
    private boolean isValid;

    public static RegisteredBankAccount createNew(String memberId, String bankName, String accountNumber, boolean isValid) {
        return new RegisteredBankAccount(null, memberId, bankName, accountNumber, isValid);
    }
}
