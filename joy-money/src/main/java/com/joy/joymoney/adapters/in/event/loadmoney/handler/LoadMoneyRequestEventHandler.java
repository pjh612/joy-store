package com.joy.joymoney.adapters.in.event.loadmoney.handler;

import com.joy.joymoney.adapters.in.event.loadmoney.LoadMoneyRequestCreatedEvent;
import com.joy.joymoney.adapters.in.event.loadmoney.payload.LoadMoneyRequestedEventPayload;
import com.joy.joymoney.domain.model.ChangingType;
import com.joy.joymoney.domain.model.Money;
import com.joy.joymoney.domain.model.MoneyChangingRequest;
import com.joy.joymoney.domain.repository.MoneyChangingRequestRepository;
import com.joy.joymoney.domain.repository.MoneyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class LoadMoneyRequestEventHandler {
    private final MoneyChangingRequestRepository moneyChangingRequestRepository;
    private final MoneyRepository moneyRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void handle(UUID sagaId, LoadMoneyRequestedEventPayload payload) {
        if ("CANCEL".equals(payload.getType())) {
            MoneyChangingRequest moneyChangingRequest = moneyChangingRequestRepository.findById(payload.getLoadMoneyRequestId())
                    .orElseThrow(RuntimeException::new);

            moneyChangingRequest.fail();
            publishInvalidEvent(sagaId);
            moneyChangingRequestRepository.save(moneyChangingRequest);
        } else {
            MoneyChangingRequest moneyChangingRequest = MoneyChangingRequest.createNew(
                    payload.getLoadMoneyRequestId(),
                    payload.getMemberId(),
                    ChangingType.INCREASE,
                    payload.getAmount()
            );

            moneyRepository.findByMemberId(payload.getMemberId())
                    .ifPresentOrElse(
                            it -> publishValidEvent(sagaId, payload, it),
                            () -> {
                                publishInvalidEvent(sagaId);
                                moneyChangingRequest.fail();
                            }

                    );
            moneyChangingRequestRepository.save(moneyChangingRequest);
        }
    }

    public void publishValidEvent(UUID sagaId, LoadMoneyRequestedEventPayload payload, Money money) {
        eventPublisher.publishEvent(new LoadMoneyRequestCreatedEvent(sagaId, payload.getLoadMoneyRequestId(), money.getId(), payload.getMemberId(), payload.getAmount(), "SUCCEEDED"));
    }

    public void publishInvalidEvent(UUID sagaId) {
        eventPublisher.publishEvent(new LoadMoneyRequestCreatedEvent(sagaId, null, null, null, null, "FAILED"));
    }
}
