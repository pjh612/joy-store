package com.joy.joybanking.adapter.in.controller;

import com.joy.joybanking.appliation.dto.QueryRegisteredBankAccountRequest;
import com.joy.joybanking.appliation.dto.QueryRegisteredBankAccountResponse;
import com.joy.joybanking.appliation.dto.RegisterAccountRequest;
import com.joy.joybanking.appliation.dto.RegisterBankAccountResponse;
import com.joy.joybanking.appliation.usecase.QueryRegisteredBankAccountUseCase;
import com.joy.joybanking.appliation.usecase.RegisterBankAccountUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/banking/account")
public class RegisteredAccountController {
    private final RegisterBankAccountUseCase registerAccountUseCase;
    private final QueryRegisteredBankAccountUseCase queryRegisteredBankAccountUseCase;

    @PostMapping
    public RegisterBankAccountResponse registerBankAccount(@RequestBody RegisterAccountRequest request) {
        return registerAccountUseCase.register(request);
    }

    @GetMapping
    public QueryRegisteredBankAccountResponse getBankAccount(@RequestParam String memberId) {
        return queryRegisteredBankAccountUseCase.query(new QueryRegisteredBankAccountRequest(memberId));
    }
}
