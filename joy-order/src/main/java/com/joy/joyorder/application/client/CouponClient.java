package com.joy.joyorder.application.client;

import com.joy.joyorder.application.client.dto.CouponResponse;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface CouponClient {
    List<CouponResponse> findByCouponIdIn(Collection<UUID> couponIds);

    void useCoupons(List<UUID> couponIds);
}
