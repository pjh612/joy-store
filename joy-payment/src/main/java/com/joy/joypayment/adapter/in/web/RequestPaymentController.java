package com.joy.joypayment.adapter.in.web;

import com.joy.joypayment.application.dto.CreatePaymentRequest;
import com.joy.joypayment.application.dto.PreparePaymentRequest;
import com.joy.joypayment.application.usecase.RequestPaymentUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RequestPaymentController {
    private final RequestPaymentUseCase requestPaymentUseCase;

    @PostMapping("/api/payment-request-prepare")
    public UUID preparePaymentRequest(@RequestBody PreparePaymentRequest request) {
        return requestPaymentUseCase.preparePayment(request);
    }

    @PostMapping("/api/payment-request")
    public void requestPayment(@RequestBody CreatePaymentRequest request) {
        requestPaymentUseCase.startPaymentSaga(request);
    }
}
