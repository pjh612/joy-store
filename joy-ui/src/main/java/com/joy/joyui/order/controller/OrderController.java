package com.joy.joyui.order.controller;

import com.joy.joycommon.api.response.ApiResponse;
import com.joy.joyui.order.client.OrderClient;
import com.joy.joyui.order.dto.FindOrderResponse;
import com.joy.joyui.order.dto.PlaceOrderRequest;
import com.joy.joyui.order.dto.PlaceOrderResponse;
import com.joy.joyui.security.StoreMemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderClient orderClient;

    @GetMapping
    public ApiResponse<List<FindOrderResponse>> getAllByMemberId(@AuthenticationPrincipal StoreMemberDetails storeMemberDetails) {
        return orderClient.getAllByMemberId(storeMemberDetails.getId());
    }

    @PostMapping
    public ApiResponse<PlaceOrderResponse> placeOrder(@RequestBody PlaceOrderRequest request, @AuthenticationPrincipal StoreMemberDetails storeMemberDetails) {
        PlaceOrderRequest orderRequest = new PlaceOrderRequest(request.orderItems(), request.couponSequence(), storeMemberDetails.getId());

        return orderClient.placeOrder(orderRequest);
    }
}
