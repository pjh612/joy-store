package com.joy.joypayment.adapter.in.web;

import com.joy.joypayment.application.client.MoneyClient;
import com.joy.joypayment.application.client.dto.MoneyResponse;
import com.joy.joypayment.application.dto.PaymentResponse;
import com.joy.joypayment.application.usecase.QueryPaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/pay")
@RequiredArgsConstructor
public class RequestPaymentUiController {
    private final MoneyClient moneyClient;
    private final QueryPaymentUseCase queryPaymentUseCase;

    @GetMapping("/{paymentRequestKey}")
    public String payRequest(Model model, @PathVariable UUID paymentRequestKey) {
        MoneyResponse moneyResponse = moneyClient.getMemberById();
        PaymentResponse paymentResponse = queryPaymentUseCase.queryById(paymentRequestKey);

        model.addAttribute("amount", paymentResponse.requestPrice());
        model.addAttribute("moneyBalance", moneyResponse.balance());

        return "pay";
    }
}
