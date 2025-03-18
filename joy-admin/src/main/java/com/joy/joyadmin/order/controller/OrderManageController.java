package com.joy.joyadmin.order.controller;

import com.joy.joyadmin.order.client.OrderClient;
import com.joy.joyadmin.order.dto.FindOrderResponse;
import com.joy.joyadmin.security.StoreSellerDetails;
import com.joy.joycommon.api.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderManageController {
    private final OrderClient orderClient;

    @GetMapping
    public ApiResponse<List<FindOrderResponse>> getAll(@AuthenticationPrincipal StoreSellerDetails storeSellerDetails, @RequestParam(required = false) UUID lastId, int size) {
        return orderClient.getAllBySellerId(storeSellerDetails.getId(), lastId, size);
    }

    @GetMapping(value = "/alarm/subscription", produces = {"text/event-stream"})
    public Flux<ServerSentEvent<String>> subscribeAlarm(@RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId,
                                                        @AuthenticationPrincipal StoreSellerDetails storeSellerDetails) {
        return orderClient.subscribeAlarm(storeSellerDetails.getId(), lastEventId);
    }
}
