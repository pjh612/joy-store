package com.joy.joyadmin.order.client;

import com.joy.joyadmin.order.dto.FindOrderResponse;
import com.joy.joycommon.api.response.ApiResponse;
import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;

import java.util.List;

public interface OrderClient {
    ApiResponse<List<FindOrderResponse>> getAllBySellerSequence(String id);

    Flux<ServerSentEvent<String>> subscribeAlarm(String memberId, String lastEventId);
}
