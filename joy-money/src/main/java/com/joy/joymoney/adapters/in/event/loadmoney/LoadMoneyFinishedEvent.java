package com.joy.joymoney.adapters.in.event.loadmoney;

import com.joy.joycommon.event.OutboxEvent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoadMoneyFinishedEvent implements OutboxEvent<String, LoadMoneyFinishedEvent> {
    private UUID sagaId;
    private String type;
    private UUID loadMoneyRequestId;
    private Instant timestamp;

    public LoadMoneyFinishedEvent(UUID sagaID, String type, UUID loadMoneyRequestId) {
        this.sagaId = sagaID;
        this.type = type;
        this.loadMoneyRequestId = loadMoneyRequestId;
        this.timestamp = Instant.now();
    }

    @Override
    public String aggregateId() {
        return this.sagaId.toString();
    }

    @Override
    public String aggregateType() {
        return "LoadMoneyFinishedEvent";
    }

    @Override
    public String type() {
        return this.type;
    }

    @Override
    public Instant timestamp() {
        return this.timestamp;
    }

    @Override
    public LoadMoneyFinishedEvent payload() {
        return this;
    }
}
