package com.joy.joymoney.adapters.in.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.joy.joycommon.event.OutboxEvent;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoadMoneyRequestCreatedEvent implements OutboxEvent<String, String> {
    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());
    private UUID sagaId;
    private String loadMoneyRequestId;
    private String moneyId;
    private String memberId;
    private BigDecimal amount;
    private String type;
    private Instant timestamp;
    private String payload;

    public LoadMoneyRequestCreatedEvent(UUID sagaId, String loadMoneyRequestId, String moneyId, String memberId, BigDecimal amount, String type) {
        this.sagaId = sagaId;
        this.loadMoneyRequestId = loadMoneyRequestId;
        this.moneyId = moneyId;
        this.memberId = memberId;
        this.amount = amount;
        this.type = type;
        this.timestamp = Instant.now();

        try {
            this.payload = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
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
    public String payload() {
        return this.payload;
    }
}
