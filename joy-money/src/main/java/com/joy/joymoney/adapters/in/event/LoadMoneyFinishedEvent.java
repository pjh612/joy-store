package com.joy.joymoney.adapters.in.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.joy.joycommon.event.OutboxEvent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoadMoneyFinishedEvent implements OutboxEvent<String, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    private UUID sagaId;
    private String type;
    private String loadMoneyRequestId;
    private Instant timestamp;
    private String payload;

    public LoadMoneyFinishedEvent(UUID sagaID, String type, String loadMoneyRequestId) {
        this.sagaId = sagaID;
        this.type = type;
        this.loadMoneyRequestId = loadMoneyRequestId;
        this.timestamp = Instant.now();
        try {
            this.payload = objectMapper.writeValueAsString(this);
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
    public String payload() {
        return this.payload;
    }
}
