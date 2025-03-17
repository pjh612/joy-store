package com.joy.joyorder.adapters.out.client;

import com.joy.joyorder.application.client.CouponClient;
import com.joy.joyorder.application.client.dto.CouponResponse;
import com.joy.joyorder.application.client.dto.FindCouponRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CouponClientAdapter implements CouponClient {
    private final RestClient couponClient;

    @Override
    public List<CouponResponse> findByCouponIdIn(Collection<UUID> couponIds) {
        return couponClient.post()
                .uri("/api/coupons")
                .body(new FindCouponRequest(couponIds))
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public void useCoupons(List<UUID> couponIds) {
        couponClient.post()
                .uri("/api/coupons/use")
                .body(couponIds)
                .retrieve();
    }
}
