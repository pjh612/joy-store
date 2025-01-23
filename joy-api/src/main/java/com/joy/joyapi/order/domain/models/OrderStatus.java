package com.joy.joyapi.order.domain.models;

import com.joy.joycommon.type.TypeModel;

public enum OrderStatus implements TypeModel {
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
}
