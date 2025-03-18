package com.joy.joyui.pay.controller;

import com.joy.joyui.pay.client.PreparePaymentResponse;
import com.joy.joyui.pay.dto.ConfirmPaymentRequest;
import com.joy.joyui.pay.dto.PreparePaymentRequest;
import com.joy.joyui.pay.service.OrderPayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderPayController {
    private final OrderPayService orderPayService;

    @ResponseBody
    @PostMapping("/api/payment/prepare")
    public PreparePaymentResponse preparePayment(@RequestBody PreparePaymentRequest request) {
        return orderPayService.preparePayment(request);
    }

    @ResponseBody
    @PostMapping("/api/payment/confirm")
    public void confirmPayment(@RequestBody ConfirmPaymentRequest request) {
        orderPayService.confirmPayment(request);
    }

    @ResponseBody
    @GetMapping(value = "/api/payment/result/subscribe", produces = "text/event-stream")
    public Flux<ServerSentEvent<String>> subscribeAndConfirmOrder(
            @RequestParam UUID paymentId,
            @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {
        return orderPayService.subscribePaymentResultAndConfirmOrder(paymentId, lastEventId);
    }


    @GetMapping("/pay-success")
    public String preparePayment(@RequestParam String paymentId, @RequestParam String requestPrice) {
        return "success";
    }
}
