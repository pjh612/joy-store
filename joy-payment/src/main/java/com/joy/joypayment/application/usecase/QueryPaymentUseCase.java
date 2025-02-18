package com.joy.joypayment.application.usecase;

import com.joy.joypayment.application.dto.PaymentResponse;

import java.util.UUID;

public interface QueryPaymentUseCase {

    PaymentResponse queryById(UUID requestId);
}
