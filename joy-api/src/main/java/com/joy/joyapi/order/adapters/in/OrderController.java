package com.joy.joyapi.order.adapters.in;

import com.joy.joyapi.order.application.usecase.PlaceOrderUseCase;
import com.joy.joyapi.order.application.usecase.QueryOrderUseCase;
import com.joy.joyapi.order.application.usecase.dto.FindOrderResponse;
import com.joy.joyapi.order.application.usecase.dto.PlaceOrderRequest;
import com.joy.joyapi.order.application.usecase.dto.PlaceOrderResponse;
import com.joy.joycommon.api.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final QueryOrderUseCase queryOrderUsecase;
    private final PlaceOrderUseCase placeOrderUseCase;

    @GetMapping
    public ApiResponse<List<FindOrderResponse>> getAllByMemberSequence(@RequestParam Long sequence) {
        return ApiResponse.of(queryOrderUsecase.queryByMemberSequence(sequence));
    }

    @PostMapping
    public ApiResponse<PlaceOrderResponse> placeOrder(@RequestBody PlaceOrderRequest request) {
        return ApiResponse.of(placeOrderUseCase.order(request));
    }
}
