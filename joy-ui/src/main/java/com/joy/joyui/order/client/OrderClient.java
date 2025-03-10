package com.joy.joyui.order.client;

import com.joy.joycommon.api.response.ApiResponse;
import com.joy.joyui.order.dto.*;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface OrderClient {
    Mono<ApiResponse<ConfirmOrderResponse>> confirmOrder(ConfirmOrderRequest request);

    ApiResponse<List<FindOrderResponse>> getByCriteria(UUID buyerId, Collection<String> excludeStatus, String direction, UUID lastId, int size);

    ApiResponse<CreateProvisionalOrderResponse> createProvisionalOrder(CreateProvisionalOrderCommand request);

    ApiResponse<FindOrderResponse> getByOrderId(UUID uuid);
}
