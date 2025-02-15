package com.joy.joypayment.adapter.in.web;

import com.joy.joypayment.application.dto.CreatePaymentRequest;
import com.joy.joypayment.application.usecase.RequestPaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RequestPaymentController {
    private final RequestPaymentUseCase requestPaymentUseCase;

    @PostMapping("/api/payment-request")
    public void requestPayment(@RequestBody CreatePaymentRequest request) {
        requestPaymentUseCase.startPaymentSaga(request);
    }
}
