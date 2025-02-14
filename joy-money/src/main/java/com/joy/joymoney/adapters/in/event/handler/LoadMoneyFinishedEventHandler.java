package com.joy.joymoney.adapters.in.event.handler;

import com.joy.joymoney.adapters.in.event.LoadMoneyFinishedEvent;
import com.joy.joymoney.adapters.in.event.payload.FirmBankingFinishedPayload;
import com.joy.joymoney.domain.model.Money;
import com.joy.joymoney.domain.model.MoneyChangingRequest;
import com.joy.joymoney.domain.repository.MoneyChangingRequestRepository;
import com.joy.joymoney.domain.repository.MoneyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class LoadMoneyFinishedEventHandler {
    private final MoneyRepository moneyRepository;
    private final MoneyChangingRequestRepository moneyChangingRequestRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void handle(UUID sagaId, FirmBankingFinishedPayload payload) {
        if ("CANCEL".equals(payload.getType())) {
            processCancelRequest(sagaId, payload);
        } else {
            processSuccessRequest(sagaId, payload);
        }
    }

    private void processSuccessRequest(UUID sagaId, FirmBankingFinishedPayload payloadObject) {
        Money money = moneyRepository.findByMemberId(payloadObject.getMemberId())
                .orElseThrow(() -> new RuntimeException("Money not found for Member ID: " + payloadObject.getMemberId()));
        money.load(payloadObject.getAmount());

        MoneyChangingRequest moneyChangingRequest = moneyChangingRequestRepository.findById(payloadObject.getLoadMoneyRequestId())
                .orElseThrow(() -> new RuntimeException("MoneyChangingRequest not found for ID: " + payloadObject.getLoadMoneyRequestId()));
        moneyChangingRequest.success();

        moneyRepository.save(money);
        moneyChangingRequestRepository.save(moneyChangingRequest);

        publishLoadMoneyFinishedEvent(sagaId, "SUCCEEDED", moneyChangingRequest.getId());
    }


    private void processCancelRequest(UUID sagaId, FirmBankingFinishedPayload payloadObject) {
        MoneyChangingRequest moneyChangingRequest = moneyChangingRequestRepository.findById(payloadObject.getLoadMoneyRequestId())
                .orElseThrow(() -> new RuntimeException("MoneyChangingRequest not found for ID: " + payloadObject.getLoadMoneyRequestId()));
        moneyChangingRequest.fail();
        moneyChangingRequestRepository.save(moneyChangingRequest);

        publishLoadMoneyFinishedEvent(sagaId, "FAILED", null);
    }

    public void publishLoadMoneyFinishedEvent(UUID sagaId, String status, UUID requestId) {
        eventPublisher.publishEvent(new LoadMoneyFinishedEvent(sagaId, status, requestId));
    }
}
