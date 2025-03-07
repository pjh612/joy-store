package com.joy.joyapi.order.application.usecase;

import com.joy.joyapi.order.application.usecase.dto.CreateProvisionalOrderRequest;
import com.joy.joyapi.order.application.usecase.dto.CreateProvisionalOrderResponse;
import com.joy.joyapi.order.application.usecase.dto.ConfirmOrderRequest;
import com.joy.joyapi.order.application.usecase.dto.ConfirmOrderResponse;

public interface PlaceOrderUseCase {
    ConfirmOrderResponse confirmOrder(ConfirmOrderRequest request);

    CreateProvisionalOrderResponse createProvisionalOrder(CreateProvisionalOrderRequest request);
}
