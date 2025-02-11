package com.joy.joybanking.adapter.in.event.handler;

import com.joy.joybanking.adapter.in.event.RequestFirmBankingFinishedEvent;
import com.joy.joybanking.adapter.in.event.payload.RequestFirmBankingEventPayload;
import com.joy.joybanking.appliation.dto.RequestFirmBankingRequest;
import com.joy.joybanking.appliation.dto.RequestFirmBankingResponse;
import com.joy.joybanking.appliation.usecase.RequestFirmBankingUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RequestFirmBankingFinishedEventHandler {
    private final RequestFirmBankingUseCase requestFirmBankingUseCase;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void handle(UUID sagaId, RequestFirmBankingEventPayload payload) {
        if ("CANCEL".equals(payload.getType())) {
            eventPublisher.publishEvent(new RequestFirmBankingFinishedEvent(
                    sagaId,
                    null,
                    payload.getLoadMoneyRequestId(),
                    payload.getMemberId(),
                    payload.getFromBankName(),
                    payload.getFromBankAccountNumber(),
                    payload.getToBankName(),
                    payload.getToBankAccountNumber(),
                    payload.getAmount(),
                    "FAILED"
            ));
        } else {
            RequestFirmBankingRequest requestFirmBankingRequest = new RequestFirmBankingRequest(
                    payload.getFromBankName(),
                    payload.getFromBankAccountNumber(),
                    payload.getToBankName(),
                    payload.getToBankAccountNumber(),
                    payload.getAmount()
            );
            RequestFirmBankingResponse requestFirmBankingResponse = requestFirmBankingUseCase.request(requestFirmBankingRequest);

            eventPublisher.publishEvent(new RequestFirmBankingFinishedEvent(
                    sagaId,
                    requestFirmBankingResponse.id(),
                    payload.getLoadMoneyRequestId(),
                    payload.getMemberId(),
                    payload.getFromBankName(),
                    payload.getFromBankAccountNumber(),
                    payload.getToBankName(),
                    payload.getToBankAccountNumber(),
                    payload.getAmount(),
                    requestFirmBankingResponse.status()
            ));
        }
    }
}
