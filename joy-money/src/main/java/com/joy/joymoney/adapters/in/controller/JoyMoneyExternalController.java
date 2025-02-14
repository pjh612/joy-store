package com.joy.joymoney.adapters.in.controller;

import com.joy.joymoney.application.dto.MoneyInfoResponse;
import com.joy.joymoney.application.usecase.QueryMoneyByMemberIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/external")
public class JoyMoneyExternalController {
    private final QueryMoneyByMemberIdUseCase queryMoneyByMemberIdUseCase;

    @GetMapping("/money")
    public MoneyInfoResponse getMoneyInfo(@AuthenticationPrincipal Jwt jwt) {
        return queryMoneyByMemberIdUseCase.query(jwt.getClaim("id"));
    }
}
