package com.joy.joymoney.application.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.joy.joycommon.event.OutboxEvent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoadMoneyRequestEvent implements OutboxEvent<String, String> {
    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());
    private UUID loadMoneyRequestId;
    private String memberId;
    private BigDecimal amount;
    private Instant timestamp;
    private String payload;

    public LoadMoneyRequestEvent(UUID loadMoneyRequestId, String memberId, BigDecimal amount) {
        this.loadMoneyRequestId = loadMoneyRequestId;
        this.memberId = memberId;
        this.amount = amount;
        this.timestamp = Instant.now();

        try {
            this.payload = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String aggregateId() {
        return this.loadMoneyRequestId.toString();
    }

    @Override
    public String aggregateType() {
        return "LoadMoneyRequestEvent";
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
    public String payload() {
        return this.payload;
    }
}
