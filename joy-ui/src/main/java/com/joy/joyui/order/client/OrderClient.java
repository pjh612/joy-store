package com.joy.joyui.order.client;

import com.joy.joycommon.api.response.ApiResponse;
import com.joy.joyui.order.dto.FindOrderResponse;
import com.joy.joyui.order.dto.PlaceOrderRequest;
import com.joy.joyui.order.dto.PlaceOrderResponse;

import java.util.List;

public interface OrderClient {
    ApiResponse<PlaceOrderResponse> placeOrder(PlaceOrderRequest request);

    ApiResponse<List<FindOrderResponse>> getAllByMemberId(String memberId);
}
