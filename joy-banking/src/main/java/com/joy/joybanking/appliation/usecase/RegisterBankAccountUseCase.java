package com.joy.joybanking.appliation.usecase;

import com.joy.joybanking.appliation.dto.RegisterAccountRequest;
import com.joy.joybanking.appliation.dto.RegisterBankAccountResponse;

public interface RegisterBankAccountUseCase {
    RegisterBankAccountResponse register(RegisterAccountRequest request);
}
