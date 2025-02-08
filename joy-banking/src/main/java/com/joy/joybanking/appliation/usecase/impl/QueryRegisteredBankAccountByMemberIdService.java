package com.joy.joybanking.appliation.usecase.impl;

import com.joy.joybanking.appliation.dto.QueryRegisteredBankAccountRequest;
import com.joy.joybanking.appliation.dto.QueryRegisteredBankAccountResponse;
import com.joy.joybanking.appliation.usecase.QueryRegisteredBankAccountUseCase;
import com.joy.joybanking.domain.model.RegisteredBankAccount;
import com.joy.joybanking.domain.repository.RegisteredAccountRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueryRegisteredBankAccountByMemberIdService implements QueryRegisteredBankAccountUseCase {
    private final RegisteredAccountRepository repository;

    @Override
    public QueryRegisteredBankAccountResponse query(QueryRegisteredBankAccountRequest request) {
        RegisteredBankAccount registeredAccount = repository.findByMemberId(request.memberId())
                .orElseThrow(EntityNotFoundException::new);

        return new QueryRegisteredBankAccountResponse(
                registeredAccount.getId(),
                registeredAccount.getBankName(),
                registeredAccount.getAccountNumber()
        );
    }
}
