package com.joy.joypayment.application.usecase.impl;

import com.fasterxml.uuid.Generators;
import com.joy.joypayment.application.client.BankingClient;
import com.joy.joypayment.application.client.MemberClient;
import com.joy.joypayment.application.client.MoneyClient;
import com.joy.joypayment.application.client.dto.MemberResponse;
import com.joy.joypayment.application.client.dto.MoneyResponse;
import com.joy.joypayment.application.client.dto.RegisteredBankAccountResponse;
import com.joy.joypayment.application.dto.CreatePaymentRequest;
import com.joy.joypayment.application.dto.PaymentResponse;
import com.joy.joypayment.application.event.PaymentRequestEvent;
import com.joy.joypayment.application.usecase.RequestPaymentUseCase;
import com.joy.joypayment.domain.model.PaymentRequest;
import com.joy.joypayment.domain.repository.PaymentRequestRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestPaymentService implements RequestPaymentUseCase {
    private final MemberClient memberClient;
    private final BankingClient bankingClient;
    private final MoneyClient moneyClient;
    private final PaymentRequestRepository paymentRequestRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public void startPaymentSaga(CreatePaymentRequest request) {
        log.info("validate member");
        MemberResponse member = memberClient.getMemberById(request.requestMemberId());
        if (member == null) {
            throw new IllegalArgumentException("잘못된 유저 정보");
        }

        log.info("validate bank account");
        RegisteredBankAccountResponse registeredBankAccount = bankingClient.getRegisteredBankAccount(request.requestMemberId());
        if (registeredBankAccount == null) {
            throw new IllegalArgumentException("유효하지 않은 계좌");
        }

        log.info("validate money");
        MoneyResponse moneyResponse = moneyClient.getMemberById();
        if (moneyResponse == null) {
            throw new IllegalArgumentException("머니 정보가 없습니다.");
        }

        log.info("validate requestPrice");
        if (moneyResponse.balance().compareTo(request.requestPrice()) < 0) {
            throw new IllegalArgumentException("충전이 필요합니다.");
        }

        eventPublisher.publishEvent(new PaymentRequestEvent(Generators.timeBasedEpochGenerator().generate(),
                request.sellerId(),
                request.requestMemberId(),
                request.requestPrice()));
    }

    @Override
    @Transactional
    public PaymentResponse requestPayment(CreatePaymentRequest request) {
        PaymentRequest paymentRequest = PaymentRequest.createNew(request.requestId(), request.requestMemberId(), request.requestPrice(), request.sellerId());

        return PaymentResponse.of(paymentRequestRepository.save(paymentRequest));
    }

    @Override
    @Transactional
    public void failRequest(UUID requestId) {
        PaymentRequest paymentRequest = paymentRequestRepository.findById(requestId)
                .orElse(null);
        if (paymentRequest == null) {
            return;
        }
        paymentRequest.fail();

        paymentRequestRepository.save(paymentRequest);
    }
}
