package com.joy.joypayment.application.usecase;

import com.joy.joypayment.application.dto.CreatePaymentRequest;
import com.joy.joypayment.application.dto.PaymentResponse;

import java.util.UUID;

public interface RequestPaymentUseCase {
    void startPaymentSaga(CreatePaymentRequest request);

    PaymentResponse requestPayment(CreatePaymentRequest request);

    void failRequest(UUID requestId);
}
