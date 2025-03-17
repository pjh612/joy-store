package com.joy.joycoupon.adapters.in.web;

import com.joy.joycoupon.application.dto.UseCouponRequest;
import com.joy.joycoupon.application.dto.CouponResponse;
import com.joy.joycoupon.application.usecase.QueryCouponUseCase;
import com.joy.joycoupon.application.usecase.UseCouponUseCase;
import com.joy.joycoupon.domain.repository.CouponCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CouponController {
    private final UseCouponUseCase useCouponUseCase;
    private final QueryCouponUseCase queryCouponUseCase;

    @PostMapping("/api/coupons/use")
    public void useCoupons(@RequestBody UseCouponRequest request) {
        useCouponUseCase.useCoupons(request);
    }

    @PostMapping("/api/coupons")
    public List<CouponResponse> findAll(@RequestBody CouponCriteria criteria) {
        return queryCouponUseCase.query(criteria);
    }
}
