package com.joy.joyapi.order.adapters.in;

import com.joy.joyapi.order.application.usecase.PlaceOrderUseCase;
import com.joy.joyapi.order.application.usecase.QueryOrderUseCase;
import com.joy.joyapi.order.application.usecase.SubscribePlaceOrderAlarmUseCase;
import com.joy.joyapi.order.application.usecase.criteria.QueryOrderCriteria;
import com.joy.joyapi.order.application.usecase.dto.*;
import com.joy.joycommon.api.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final QueryOrderUseCase queryOrderUsecase;
    private final PlaceOrderUseCase placeOrderUseCase;
    private final SubscribePlaceOrderAlarmUseCase subscribePlaceOrderAlarmUseCase;

    @GetMapping
    public ApiResponse<List<FindOrderResponse>> getByCriteria(QueryOrderCriteria criteria) {
        return ApiResponse.of(queryOrderUsecase.queryByCriteria(criteria));
    }

    @PostMapping
    public ApiResponse<ConfirmOrderResponse> confirmOrder(@Valid @RequestBody ConfirmOrderRequest request) {
        return ApiResponse.of(placeOrderUseCase.confirmOrder(request));
    }

    @GetMapping(value = "/alarm/subscription", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribeAlarm(@RequestParam Long memberId, @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {
        return subscribePlaceOrderAlarmUseCase.subscribe(memberId.toString(), lastEventId);
    }

    @GetMapping("/{orderId}")
    public ApiResponse<FindOrderResponse> getByOrderId(@PathVariable UUID orderId) {
        return ApiResponse.of(queryOrderUsecase.queryByOrderId(orderId));
    }


    @PostMapping("/provisional")
    public ApiResponse<CreateProvisionalOrderResponse> createProvisionalOrder(@Valid @RequestBody CreateProvisionalOrderRequest request) {
        return ApiResponse.of(placeOrderUseCase.createProvisionalOrder(request));
    }
}
