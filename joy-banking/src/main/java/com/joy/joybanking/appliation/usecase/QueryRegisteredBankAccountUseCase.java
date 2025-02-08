package com.joy.joybanking.appliation.usecase;

import com.joy.joybanking.appliation.dto.QueryRegisteredBankAccountRequest;
import com.joy.joybanking.appliation.dto.QueryRegisteredBankAccountResponse;

public interface QueryRegisteredBankAccountUseCase {
    QueryRegisteredBankAccountResponse query(QueryRegisteredBankAccountRequest request);

}
