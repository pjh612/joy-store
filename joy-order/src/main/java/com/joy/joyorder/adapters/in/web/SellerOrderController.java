package com.joy.joyorder.adapters.in.web;

import com.joy.joyorder.application.usecase.QueryOrderUseCase;
import com.joy.joyorder.application.usecase.criteria.QueryOrderBySellerIdCriteria;
import com.joy.joyorder.application.usecase.dto.FindOrderResponse;
import com.joy.joycommon.api.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sellers")
public class SellerOrderController {
    private final QueryOrderUseCase queryOrderUsecase;

    @GetMapping("/{sellerId}/orders")
    public ApiResponse<List<FindOrderResponse>> getAllBySellerSequence(@PathVariable UUID sellerId, QueryOrderBySellerIdCriteria criteria) {
        return ApiResponse.of(queryOrderUsecase.queryBySellerId(criteria));
    }
}
