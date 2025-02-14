package com.joy.joymoney.application.usecase.impl;

import com.joy.joymoney.application.dto.CreateMoneyRequest;
import com.joy.joymoney.application.usecase.CreateMoneyUseCase;
import com.joy.joymoney.domain.model.Money;
import com.joy.joymoney.domain.repository.MoneyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateMoneyService implements CreateMoneyUseCase {
    private final MoneyRepository moneyRepository;

    @Override
    public UUID create(CreateMoneyRequest request) {
        Money money = Money.createNew(request.memberId());
        moneyRepository.save(money);

        return money.getId();
    }
}
