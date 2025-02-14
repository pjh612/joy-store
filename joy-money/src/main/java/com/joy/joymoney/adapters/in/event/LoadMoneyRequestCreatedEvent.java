package com.joy.joymoney.adapters.in.event;

import com.joy.joycommon.event.OutboxEvent;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoadMoneyRequestCreatedEvent implements OutboxEvent<String, LoadMoneyRequestCreatedEvent> {
    private UUID sagaId;
    private UUID loadMoneyRequestId;
    private UUID moneyId;
    private UUID memberId;
    private BigDecimal amount;
    private String type;
    private Instant timestamp;

    public LoadMoneyRequestCreatedEvent(UUID sagaId, UUID loadMoneyRequestId, UUID moneyId, UUID memberId, BigDecimal amount, String type) {
        this.sagaId = sagaId;
        this.loadMoneyRequestId = loadMoneyRequestId;
        this.moneyId = moneyId;
        this.memberId = memberId;
        this.amount = amount;
        this.type = type;
        this.timestamp = Instant.now();
    }

    @Override
    public String aggregateId() {
        return this.sagaId.toString();
    }

    @Override
    public String aggregateType() {
        return "LoadMoneyRequestCreatedEvent";
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
    public LoadMoneyRequestCreatedEvent payload() {
        return this;
    }
}
