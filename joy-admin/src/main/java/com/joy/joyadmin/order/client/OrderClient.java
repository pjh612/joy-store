package com.joy.joyadmin.order.client;

import com.joy.joyadmin.order.dto.FindOrderResponse;
import com.joy.joycommon.api.response.ApiResponse;
import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

public interface OrderClient {
    ApiResponse<List<FindOrderResponse>> getAllBySellerId(UUID id);

    Flux<ServerSentEvent<String>> subscribeAlarm(UUID memberId, String lastEventId);
}
