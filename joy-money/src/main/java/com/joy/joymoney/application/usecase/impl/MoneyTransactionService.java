package com.joy.joymoney.application.usecase.impl;

import com.fasterxml.uuid.Generators;
import com.joy.joymoney.application.dto.DecreaseMoneyResponse;
import com.joy.joymoney.application.event.LoadMoneyRequestEvent;
import com.joy.joymoney.application.usecase.DecreaseMoneyUseCase;
import com.joy.joymoney.application.usecase.LoadMoneyUseCase;
import com.joy.joymoney.domain.model.ChangingType;
import com.joy.joymoney.domain.model.Money;
import com.joy.joymoney.domain.model.MoneyChangingRequest;
import com.joy.joymoney.domain.repository.MoneyChangingRequestRepository;
import com.joy.joymoney.domain.repository.MoneyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MoneyTransactionService implements LoadMoneyUseCase, DecreaseMoneyUseCase {
    private final MoneyChangingRequestRepository moneyChangingRequestRepository;
    private final MoneyRepository moneyRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public void loadMoneySaga(String memberId, BigDecimal amount) {
        LoadMoneyRequestEvent event = new LoadMoneyRequestEvent(Generators.timeBasedEpochGenerator().generate(), memberId, amount);

        eventPublisher.publishEvent(event);
    }

    @Transactional
    @Override
    public DecreaseMoneyResponse decrease(UUID requestId, UUID memberId, BigDecimal amount) {
        MoneyChangingRequest newMoneyChangingRequest = MoneyChangingRequest.createNew(requestId, memberId, ChangingType.DECREASE, amount);
        Money money = findMoneyAndDecrease(memberId, newMoneyChangingRequest);

        newMoneyChangingRequest.success();
        MoneyChangingRequest moneyChangingRequest = moneyChangingRequestRepository.save(newMoneyChangingRequest);

        return new DecreaseMoneyResponse(memberId, moneyChangingRequest.getId(), moneyChangingRequest.getStatus(), money.getBalance());
    }

    private Money findMoneyAndDecrease(UUID memberId, MoneyChangingRequest newMoneyChangingRequest) {
        Money foundMoney = moneyRepository.findByMemberId(memberId)
                .orElseThrow(EntityNotFoundException::new);

        foundMoney.decrease(newMoneyChangingRequest.getAmount());
        return moneyRepository.save(foundMoney);
    }

    @Override
    @Transactional
    public void onDecreaseFailed(UUID requestId, UUID memberId, BigDecimal amount) {
        MoneyChangingRequest foundMoneyChangingRequest = moneyChangingRequestRepository.findById(requestId)
                .orElseThrow(() -> new EntityNotFoundException("요청 정보를 찾을 수 없습니다."));
        rollbackDecreasedMoney(memberId, amount);
        foundMoneyChangingRequest.fail();
        moneyChangingRequestRepository.save(foundMoneyChangingRequest);
    }

    private void rollbackDecreasedMoney(UUID memberId, BigDecimal amount) {
        Money foundMoney = moneyRepository.findByMemberId(memberId)
                .orElseThrow(EntityNotFoundException::new);
        foundMoney.load(amount);
        moneyRepository.save(foundMoney);
    }

}
