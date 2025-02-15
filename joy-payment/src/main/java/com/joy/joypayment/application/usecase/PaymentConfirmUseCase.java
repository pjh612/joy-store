package com.joy.joypayment.application.usecase;

import com.joy.joypayment.application.dto.CompletePaymentRequest;

import java.util.UUID;

public interface PaymentConfirmUseCase {
    void confirm(CompletePaymentRequest request);

    void failConfirm(UUID requestId);
}
