package com.joy.joycoupon.application.mapper;

import com.joy.joycoupon.domain.model.AbstractCoupon;
import com.joy.joycoupon.domain.model.FixedAmountCoupon;
import com.joy.joycoupon.domain.model.RateAmountCoupon;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CouponMapperConfig {

    @Bean
    Map<Class<? extends AbstractCoupon> ,CouponMapper> couponMapperMap () {

        HashMap<Class<? extends AbstractCoupon>, CouponMapper> classCouponMapperHashMap = new HashMap<>();
        classCouponMapperHashMap.put(FixedAmountCoupon.class, new FixedAmountCouponMapper());
        classCouponMapperHashMap.put(RateAmountCoupon.class, new RateAmountCouponMapper());
        return classCouponMapperHashMap;
    }
}
