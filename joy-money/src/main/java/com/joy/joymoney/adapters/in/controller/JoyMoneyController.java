package com.joy.joymoney.adapters.in.controller;

import com.joy.joymoney.application.dto.CreateMoneyRequest;
import com.joy.joymoney.application.usecase.CreateMoneyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class JoyMoneyController {
    private final CreateMoneyUseCase createMoneyUseCase;

    @PostMapping("/money")
    public UUID createJoyMoney(@RequestBody CreateMoneyRequest request) {
        return createMoneyUseCase.create(request);
    }
}
