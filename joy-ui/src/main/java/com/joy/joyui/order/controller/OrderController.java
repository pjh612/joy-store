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
    public ApiResponse<List<FindOrderResponse>> getAllByMemberSequence(@AuthenticationPrincipal StoreMemberDetails storeMemberDetails) {
        return orderClient.getAllByMemberSequence(storeMemberDetails.getSeq());
    }

    @PostMapping
    public ApiResponse<PlaceOrderResponse> placeOrder(@RequestBody PlaceOrderRequest request, @AuthenticationPrincipal StoreMemberDetails storeMemberDetails) {
        PlaceOrderRequest orderRequest = new PlaceOrderRequest(request.orderItems(), request.couponSequence(), storeMemberDetails.getSeq());

        return orderClient.placeOrder(orderRequest);
    }
}
