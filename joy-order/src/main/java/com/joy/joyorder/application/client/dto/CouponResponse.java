package com.joy.joyorder.application.client.dto;

import java.util.UUID;

public record CouponResponse(
        UUID id,
        UUID issure,
        String couponName,
        String status,
        String type,
        double value
) {
}
