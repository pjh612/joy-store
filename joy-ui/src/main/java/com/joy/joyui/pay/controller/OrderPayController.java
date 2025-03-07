package com.joy.joyui.pay.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joy.joyui.order.client.OrderClient;
import com.joy.joyui.order.dto.ConfirmOrderRequest;
import com.joy.joyui.order.dto.FindOrderResponse;
import com.joy.joyui.pay.client.PaymentClient;
import com.joy.joyui.pay.client.PreparePaymentResponse;
import com.joy.joyui.pay.dto.ConfirmPaymentRequest;
import com.joy.joyui.pay.dto.PreparePaymentRequest;
import com.joy.joyui.security.StoreMemberDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderPayController {
    private final PaymentClient paymentClient;
    private final OrderClient orderClient;

    @ResponseBody
    @PostMapping("/api/payment/prepare")
    public PreparePaymentResponse preparePayment(@RequestBody PreparePaymentRequest request, @AuthenticationPrincipal StoreMemberDetails storeMemberDetails) {
        FindOrderResponse orderResponse = orderClient.getByOrderId(request.orderId()).getData();

        BigDecimal payAmount = orderResponse.orderItems()
                .stream()
                .map(orderItem -> orderItem.unitPrice().multiply(BigDecimal.valueOf(orderItem.quantity())).subtract(orderItem.discountAmount() == null ? BigDecimal.ZERO : orderItem.discountAmount()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);// 합계 계산

        if (payAmount.compareTo(request.requestPrice()) != 0) {
            throw new IllegalArgumentException("잘못된 결제 정보입니다.");
        }

        PreparePaymentResponse preparePaymentResponse = paymentClient.preparePayment(new com.joy.joyui.pay.client.dto.PreparePaymentRequest(request.orderId().toString(), request.requestPrice(), request.productName()));
        UUID paymentId = preparePaymentResponse.paymentId();

        return new PreparePaymentResponse(paymentId);
    }

    @ResponseBody
    @PostMapping("/api/payment/confirm")
    public void confirmPayment(@RequestBody ConfirmPaymentRequest request) {
        paymentClient.confirmPayment(request);
    }

    @ResponseBody
    @GetMapping(value = "/api/payment/result/subscribe", produces = {"text/event-stream"})
    public Flux<ServerSentEvent<String>> subscribeAlarm(@RequestParam UUID paymentId, @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {
        return paymentClient.subscribePaymentResult(paymentId, lastEventId).doOnNext(it -> {
            if (it.event() != null && it.event().equals("message")) {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    Map<String, Object> map = objectMapper.readValue(it.data(), Map.class);
                    BigDecimal requestPrice = BigDecimal.valueOf((Double) map.get("requestPrice"));
                    String orderId = (String) map.get("orderId");
                    orderClient.confirmOrder(new ConfirmOrderRequest(UUID.fromString(orderId), requestPrice));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @GetMapping("/pay-success")
    public String preparePayment(@RequestParam String paymentId, @RequestParam String requestPrice) {
        return "success";
    }
}
