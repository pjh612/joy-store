package com.joy.joybanking.domain.repository;

import com.joy.joybanking.domain.model.RegisteredBankAccount;

import java.util.Optional;

public interface RegisteredAccountRepository {
    Optional<RegisteredBankAccount> findByMemberId(String memberId);

    RegisteredBankAccount save(RegisteredBankAccount registeredAccount);
}
