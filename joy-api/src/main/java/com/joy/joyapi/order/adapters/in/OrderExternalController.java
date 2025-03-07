package com.joy.joyapi.order.adapters.in;

import com.joy.joyapi.order.application.usecase.QueryOrderSummaryUseCase;
import com.joy.joyapi.order.application.usecase.dto.OrderSummaryResponse;
import com.joy.joycommon.api.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/external/orders")
public class OrderExternalController {
    private final QueryOrderSummaryUseCase queryOrderSummaryUseCase;

    @GetMapping("/summary")
    public ApiResponse<OrderSummaryResponse> getSummaryBySellerId(@AuthenticationPrincipal Jwt jwt) {
        return ApiResponse.of(queryOrderSummaryUseCase.query(UUID.fromString((String) jwt.getClaims().get("id"))));
    }
}
