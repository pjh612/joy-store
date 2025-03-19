package com.joy.joyorder.domain.models;

import com.joy.joycommon.type.TypeModel;

import java.util.Arrays;
import java.util.List;

public enum OrderStatus implements TypeModel {
    PAYMENT_WAITING("결제 대기중"),
    ORDER_COMPLETED("주문 완료"),
    DELIVERY_PREPARING("배송 준비 중"),
    SHIPPING("배송 중"),
    SHIP_COMPLETED("배송 완료");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    public static List<OrderStatus> getVisibleStatusesForUser() {
        return Arrays.stream(OrderStatus.values()).filter(it -> it != PAYMENT_WAITING).toList();
    }
}
