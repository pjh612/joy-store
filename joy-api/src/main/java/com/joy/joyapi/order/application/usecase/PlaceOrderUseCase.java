package com.joy.joyapi.order.application.usecase;

import com.joy.joyapi.order.application.usecase.dto.PlaceOrderRequest;
import com.joy.joyapi.order.application.usecase.dto.PlaceOrderResponse;

public interface PlaceOrderUseCase {
    PlaceOrderResponse order(PlaceOrderRequest request);
}
