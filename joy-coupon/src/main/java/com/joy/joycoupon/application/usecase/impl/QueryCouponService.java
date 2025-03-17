package com.joy.joycoupon.application.usecase.impl;

import com.joy.joycoupon.application.dto.CouponResponse;
import com.joy.joycoupon.application.usecase.QueryCouponUseCase;
import com.joy.joycoupon.application.mapper.CouponMapper;
import com.joy.joycoupon.domain.model.AbstractCoupon;
import com.joy.joycoupon.domain.repository.CouponCriteria;
import com.joy.joycoupon.domain.repository.CouponRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QueryCouponService implements QueryCouponUseCase {
    private final CouponRepository couponRepository;
    private final Map<Class<? extends AbstractCoupon>, CouponMapper> couponMapperMap;

    public QueryCouponService(CouponRepository couponRepository, Map<Class<? extends AbstractCoupon>, CouponMapper> couponMapperMap) {
        this.couponRepository = couponRepository;
        this.couponMapperMap = couponMapperMap;
    }

    @Override
    public List<CouponResponse> query(CouponCriteria criteria) {
        return couponRepository.findByCriteria(criteria)
                .stream()
                .map(it -> couponMapperMap.get(it.getClass()).map(it))
                .toList();
    }
}
