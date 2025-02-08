package com.joy.joymoney.application.usecase.impl;

import com.fasterxml.uuid.Generators;
import com.joy.joymoney.application.event.LoadMoneyRequestEvent;
import com.joy.joymoney.application.usecase.LoadMoneyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class MoneyTransactionService implements LoadMoneyUseCase {
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public void loadMoneySaga(String memberId, BigDecimal amount) {
        LoadMoneyRequestEvent event = new LoadMoneyRequestEvent(Generators.timeBasedEpochGenerator().generate(), memberId, amount);

        eventPublisher.publishEvent(event);
    }
}
