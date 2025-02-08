package com.joy.joybanking.appliation.usecase.impl;

import com.fasterxml.uuid.Generators;
import com.joy.joybanking.appliation.client.BankAccountInfoClient;
import com.joy.joybanking.appliation.client.MemberClient;
import com.joy.joybanking.appliation.client.dto.BankAccount;
import com.joy.joybanking.appliation.client.dto.FindMemberResponse;
import com.joy.joybanking.appliation.dto.RegisterAccountRequest;
import com.joy.joybanking.appliation.dto.RegisterBankAccountResponse;
import com.joy.joybanking.appliation.usecase.RegisterBankAccountUseCase;
import com.joy.joybanking.domain.model.RegisteredBankAccount;
import com.joy.joybanking.domain.repository.RegisteredAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterAccountService implements RegisterBankAccountUseCase {
    private final RegisteredAccountRepository registeredAccountRepository;
    private final MemberClient memberClient;
    private final BankAccountInfoClient bankAccountInfoClient;

    @Override
    public RegisterBankAccountResponse register(RegisterAccountRequest request) {
        //멤버 확인
        FindMemberResponse memberResponse = memberClient.findByMemberId(request.memberId());
        if(memberResponse == null) {
            throw new IllegalArgumentException("유효하지 않은 회원입니다.");
        }

        //계좌 정보 확인
        BankAccount bankAccountInfo = bankAccountInfoClient.getBankAccountInfo(request.bankName(), request.bankAccountNumber());
        boolean isValid = bankAccountInfo.isValid();

        if (isValid) {
            RegisteredBankAccount registeredAccount = RegisteredBankAccount.createNew(
                    request.memberId(),
                    request.bankName(),
                    request.bankAccountNumber(),
                    isValid
            );
            RegisteredBankAccount save = registeredAccountRepository.save(registeredAccount);

            return new RegisterBankAccountResponse(save.getId());
        } else {
            throw new IllegalArgumentException("유효하지 않은 계좌");
        }
    }
}
