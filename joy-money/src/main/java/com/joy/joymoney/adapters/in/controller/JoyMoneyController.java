package com.joy.joymoney.adapters.in.controller;

import com.joy.joymoney.application.dto.CreateMoneyRequest;
import com.joy.joymoney.application.usecase.CreateMoneyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class JoyMoneyController {
    private final CreateMoneyUseCase createMoneyUseCase;

    @PostMapping("/money")
    public String createJoyMoney(@RequestBody CreateMoneyRequest request) {
        return createMoneyUseCase.create(request);
    }
}
