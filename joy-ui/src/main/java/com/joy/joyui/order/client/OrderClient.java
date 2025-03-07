package com.joy.joyui.order.client;

import com.joy.joycommon.api.response.ApiResponse;
import com.joy.joyui.order.dto.*;

import java.util.List;
import java.util.UUID;

public interface OrderClient {
    ApiResponse<ConfirmOrderResponse> confirmOrder(ConfirmOrderRequest request);

    ApiResponse<List<FindOrderResponse>> getAllByMemberId(UUID memberId);

    ApiResponse<CreateProvisionalOrderResponse> createProvisionalOrder(CreateProvisionalOrderCommand request);

    ApiResponse<FindOrderResponse> getByOrderId(UUID uuid);
}
