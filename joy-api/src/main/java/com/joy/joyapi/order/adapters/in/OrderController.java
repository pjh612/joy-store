package com.joy.joyapi.order.adapters.in;

import com.joy.joyapi.order.application.usecase.PlaceOrderUseCase;
import com.joy.joyapi.order.application.usecase.QueryOrderUseCase;
import com.joy.joyapi.order.application.usecase.SubscribePlaceOrderAlarmUseCase;
import com.joy.joyapi.order.application.usecase.dto.FindOrderResponse;
import com.joy.joyapi.order.application.usecase.dto.PlaceOrderRequest;
import com.joy.joyapi.order.application.usecase.dto.PlaceOrderResponse;
import com.joy.joycommon.api.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final QueryOrderUseCase queryOrderUsecase;
    private final PlaceOrderUseCase placeOrderUseCase;
    private final SubscribePlaceOrderAlarmUseCase subscribePlaceOrderAlarmUseCase;

    @GetMapping
    public ApiResponse<List<FindOrderResponse>> getAllByMemberId(@RequestParam String memberId) {
        return ApiResponse.of(queryOrderUsecase.queryByMemberId(memberId));
    }

    @PostMapping
    public ApiResponse<PlaceOrderResponse> placeOrder(@RequestBody PlaceOrderRequest request) {
        return ApiResponse.of(placeOrderUseCase.order(request));
    }

    @GetMapping(value = "/alarm/subscription", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribeAlarm(@RequestParam Long memberId, @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {
        return subscribePlaceOrderAlarmUseCase.subscribe(memberId.toString(), lastEventId);
    }
}
