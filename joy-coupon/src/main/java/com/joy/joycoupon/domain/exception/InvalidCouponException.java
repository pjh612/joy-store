package com.joy.joycoupon.domain.exception;

public class InvalidCouponException extends RuntimeException {
    public InvalidCouponException() {
    }

    public InvalidCouponException(String message) {
        super(message);
    }
}
