package com.joy.joycoupon.application.usecase.impl;

import com.joy.joycoupon.application.dto.UseCouponRequest;
import com.joy.joycoupon.application.usecase.UseCouponUseCase;
import com.joy.joycoupon.domain.model.AbstractCoupon;
import com.joy.joycoupon.domain.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UseCouponUseService implements UseCouponUseCase {
    private final CouponRepository couponRepository;

    @Override
    @Transactional
    public void useCoupons(UseCouponRequest request) {
        List<AbstractCoupon> coupons = couponRepository.findByIdIn(request.couponIds());
        for (AbstractCoupon coupon : coupons) {
            coupon.use();
        }

        couponRepository.saveAll(coupons);
    }
}
