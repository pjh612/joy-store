package com.joy.joyadmin.order.client;

import com.joy.joyadmin.order.dto.FindOrderResponse;
import com.joy.joycommon.api.response.ApiResponse;

import java.util.List;

public interface OrderClient {
    ApiResponse<List<FindOrderResponse>> getAllBySellerSequence(Long sequence);
}
