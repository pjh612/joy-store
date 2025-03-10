package com.joy.joyui.pay.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joy.joyui.order.client.OrderClient;
import com.joy.joyui.order.dto.ConfirmOrderRequest;
import com.joy.joyui.order.dto.FindOrderResponse;
import com.joy.joyui.pay.client.PaymentClient;
import com.joy.joyui.pay.client.PreparePaymentResponse;
import com.joy.joyui.pay.dto.ConfirmPaymentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderPayService {
    private final PaymentClient paymentClient;
    private final OrderClient orderClient;
    private final ObjectMapper objectMapper;

    private static final String ERROR_PROCESSING_JSON = "결제 데이터 처리 실패";
    private static final String ERROR_SYSTEM = "시스템 오류입니다.";
    private static final String ERROR_ORDER_CONFIRM = "주문 확인 실패";
    private static final String ERROR_INVALID_ARGUMENT = "잘못된 데이터 형식입니다.";

    public void confirmPayment(ConfirmPaymentRequest request) {
        paymentClient.confirmPayment(request);
    }

    public PreparePaymentResponse preparePayment(com.joy.joyui.pay.dto.PreparePaymentRequest request) {
        FindOrderResponse orderResponse = orderClient.getByOrderId(request.orderId()).getData();

        BigDecimal payAmount = calculatePayAmount(orderResponse);
        if (payAmount.compareTo(request.requestPrice()) != 0) {
            throw new IllegalArgumentException("잘못된 결제 정보입니다.");
        }

        PreparePaymentResponse preparePaymentResponse = paymentClient.preparePayment(new com.joy.joyui.pay.client.dto.PreparePaymentRequest(request.orderId().toString(), request.requestPrice(), request.productName()));
        UUID paymentId = preparePaymentResponse.paymentId();

        return new PreparePaymentResponse(paymentId);
    }

    private BigDecimal calculatePayAmount(FindOrderResponse orderResponse) {
        return orderResponse.orderItems()
                .stream()
                .map(orderItem -> orderItem.unitPrice().multiply(BigDecimal.valueOf(orderItem.quantity())).subtract(orderItem.discountAmount() == null ? BigDecimal.ZERO : orderItem.discountAmount()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);// 합계 계산
    }

    public Flux<ServerSentEvent<String>> subscribePaymentResultAndConfirmOrder(UUID paymentId, String lastEventId) {
        return paymentClient.subscribePaymentResult(paymentId, lastEventId)
                .flatMap(event -> {
                    if ("message".equals(event.event())) {
                        return handlePaymentEventAndConfirmOrder(event);
                    }
                    return Mono.just(createServerSentEvent(event.event(), event.data(), event.id()));
                });
    }

    private Mono<ServerSentEvent<String>> handlePaymentEventAndConfirmOrder(ServerSentEvent<String> event) {
        try {
            Map<String, Object> dataMap = parseEventData(event.data());
            int paymentStatus = (int) dataMap.get("paymentStatus");

            if (paymentStatus == 1) {
                return processSuccessfulPayment(event, dataMap);
            } else {
                return processFailedPayment(event, dataMap);
            }
        } catch (JsonProcessingException e) {
            log.error("Error processing JSON: {}", e.getMessage(), e);
            return Mono.just(createErrorEvent(event, ERROR_PROCESSING_JSON));
        } catch (IllegalArgumentException e) {
            log.error("Invalid argument: {}", e.getMessage(), e);
            return Mono.just(createErrorEvent(event, ERROR_INVALID_ARGUMENT));
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage(), e);
            return Mono.just(createErrorEvent(event, ERROR_SYSTEM));
        }
    }

    private Map<String, Object> parseEventData(String data) throws JsonProcessingException {
        return objectMapper.readValue(data, Map.class);
    }

    private Mono<ServerSentEvent<String>> processSuccessfulPayment(ServerSentEvent<String> event, Map<String, Object> dataMap) {
        BigDecimal requestPrice = BigDecimal.valueOf((Double) dataMap.get("requestPrice"));
        String orderId = (String) dataMap.get("orderId");
        ConfirmOrderRequest request = new ConfirmOrderRequest(UUID.fromString(orderId), requestPrice);

        return orderClient.confirmOrder(request)
                .map(response -> {
                    dataMap.put("orderStatus", "SUCCEEDED");
                    return createServerSentEvent(event.event(), serializeDataMap(dataMap), event.id());
                })
                .onErrorResume(e -> {
                    log.error("Error confirming order: {}", e.getMessage(), e);
                    return Mono.just(createErrorEvent(event, ERROR_ORDER_CONFIRM));
                });
    }

    private Mono<ServerSentEvent<String>> processFailedPayment(ServerSentEvent<String> event, Map<String, Object> dataMap) {
        return Mono.just(createErrorEvent(event, (String) dataMap.get("failureReason")));
    }

    private ServerSentEvent<String> createErrorEvent(ServerSentEvent<String> originalEvent, String errorMessage) {
        Map<String, Object> errorMap = Map.of(
                "orderStatus", "FAILED",
                "errorMsg", errorMessage
        );
        return createServerSentEvent(originalEvent.event(), serializeDataMap(errorMap), originalEvent.id());
    }

    private String serializeDataMap(Map<String, Object> dataMap) {
        try {
            return objectMapper.writeValueAsString(dataMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing data map", e);
        }
    }

    private ServerSentEvent<String> createServerSentEvent(String event, String data, String id) {
        return ServerSentEvent.<String>builder()
                .event(event)
                .data(data)
                .id(id)
                .build();
    }
}
