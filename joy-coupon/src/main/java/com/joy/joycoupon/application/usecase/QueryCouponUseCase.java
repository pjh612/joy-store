package com.joy.joycoupon.application.usecase;

import com.joy.joycoupon.application.dto.CouponResponse;
import com.joy.joycoupon.domain.repository.CouponCriteria;

import java.util.List;

public interface QueryCouponUseCase {
    List<CouponResponse> query(CouponCriteria criteria);
}
