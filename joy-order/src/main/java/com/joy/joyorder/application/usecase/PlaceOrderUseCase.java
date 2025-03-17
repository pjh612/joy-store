package com.joy.joyorder.application.usecase;

import com.joy.joyorder.application.usecase.dto.ConfirmOrderRequest;
import com.joy.joyorder.application.usecase.dto.ConfirmOrderResponse;
import com.joy.joyorder.application.usecase.dto.CreateProvisionalOrderRequest;
import com.joy.joyorder.application.usecase.dto.CreateProvisionalOrderResponse;

public interface PlaceOrderUseCase {
    ConfirmOrderResponse confirmOrder(ConfirmOrderRequest request);

    CreateProvisionalOrderResponse createProvisionalOrder(CreateProvisionalOrderRequest request);
}
