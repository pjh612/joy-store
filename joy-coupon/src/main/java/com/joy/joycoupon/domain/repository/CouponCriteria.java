package com.joy.joycoupon.domain.repository;

import java.util.List;
import java.util.UUID;

public record CouponCriteria(
        List<UUID> couponIds
) {
}
