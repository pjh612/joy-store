package com.joy.joyapi.order.application.usecase.event;

import com.joy.joycommon.event.OutboxEvent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaceOrderEvent implements OutboxEvent<UUID, PlaceOrderEvent> {
    private List<UUID> sellerIds;
    private UUID orderId;
    private Instant timestamp;

    public PlaceOrderEvent(List<UUID> sellerIds, UUID orderId) {
        this.sellerIds = sellerIds;
        this.orderId = orderId;
        this.timestamp = Instant.now();
    }

    @Override
    public UUID aggregateId() {
        return this.orderId;
    }

    @Override
    public String aggregateType() {
        return "PlaceOrderEvent";
    }

    @Override
    public String type() {
        return "REQUEST";
    }

    @Override
    public Instant timestamp() {
        return this.timestamp;
    }

    @Override
    public PlaceOrderEvent payload() {
        return this;
    }
}
