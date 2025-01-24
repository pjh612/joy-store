package com.joy.joyapi.order.adapters.in;

import com.joy.joyapi.order.application.usecase.PlaceOrderUseCase;
import com.joy.joyapi.order.application.usecase.QueryOrderUseCase;
import com.joy.joyapi.order.application.usecase.SubscribePlaceOrderAlarmUseCase;
import com.joy.joyapi.order.application.usecase.dto.FindOrderResponse;
import com.joy.joyapi.order.application.usecase.dto.OrderRequest;
import com.joy.joyapi.order.application.usecase.dto.OrderResponse;
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

    @GetMapping
    public ApiResponse<List<FindOrderResponse>> getAllByMemberSequence(@RequestParam Long sequence) {
        return ApiResponse.of(queryOrderUsecase.queryByMemberSequence(sequence));
    }
}
