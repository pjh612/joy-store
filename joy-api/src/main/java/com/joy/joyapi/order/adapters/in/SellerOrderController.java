package com.joy.joyapi.order.adapters.in;

import com.joy.joyapi.order.application.usecase.QueryOrderUseCase;
import com.joy.joyapi.order.application.usecase.dto.FindOrderResponse;
import com.joy.joycommon.api.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sellers")
public class SellerOrderController {
    private final QueryOrderUseCase queryOrderUsecase;

    @GetMapping("/{sellerSequence}/orders")
    public ApiResponse<List<FindOrderResponse>> getAllBySellerSequence(@PathVariable Long sellerSequence) {
        return ApiResponse.of(queryOrderUsecase.queryBySellerSequence(sellerSequence));
    }
}
