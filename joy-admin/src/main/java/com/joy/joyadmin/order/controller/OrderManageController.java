package com.joy.joyadmin.order.controller;

import com.joy.joyadmin.order.client.OrderClient;
import com.joy.joyadmin.order.dto.FindOrderResponse;
import com.joy.joyadmin.security.StoreSellerDetails;
import com.joy.joycommon.api.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderManageController {
    private final OrderClient orderClient;

    @GetMapping
    public ApiResponse<List<FindOrderResponse>> getAll(@AuthenticationPrincipal StoreSellerDetails storeSellerDetails) {
        return orderClient.getAllBySellerSequence(storeSellerDetails.getSeq());
    }
}
