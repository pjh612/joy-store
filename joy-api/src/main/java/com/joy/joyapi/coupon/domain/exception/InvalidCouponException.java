package com.joy.joyapi.coupon.domain.exception;

public class InvalidCouponException extends RuntimeException {
    public InvalidCouponException() {
    }

    public InvalidCouponException(String message) {
        super(message);
    }
}
