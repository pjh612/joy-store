package com.joy.joyui.order.controller;

import com.joy.joycommon.api.response.ApiResponse;
import com.joy.joyui.order.client.OrderClient;
import com.joy.joyui.order.dto.CreateProvisionalOrderCommand;
import com.joy.joyui.order.dto.CreateProvisionalOrderRequest;
import com.joy.joyui.order.dto.CreateProvisionalOrderResponse;
import com.joy.joyui.order.dto.FindOrderResponse;
import com.joy.joyui.security.StoreMemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderClient orderClient;

    @GetMapping
    public ApiResponse<List<FindOrderResponse>> getByCriteria(@RequestParam(value = "lastId", required = false) UUID lastId, @AuthenticationPrincipal StoreMemberDetails storeMemberDetails) {
        return orderClient.getByCriteria(storeMemberDetails.getId(), List.of("PAYMENT_WAITING"), "desc", lastId, 5);
    }

    @PostMapping("/prepare")
    public ApiResponse<CreateProvisionalOrderResponse> prepareOrder(@RequestBody CreateProvisionalOrderRequest request, @AuthenticationPrincipal StoreMemberDetails storeMemberDetails) {
        CreateProvisionalOrderCommand command = new CreateProvisionalOrderCommand(
                request.orderItems().stream().map(it -> new CreateProvisionalOrderCommand.OrderItemRequestDto(it.itemId(), it.couponId(), it.quantity())).toList(),
                storeMemberDetails.getId(),
                request.payType()
        );

        return orderClient.createProvisionalOrder(command);
    }
}
