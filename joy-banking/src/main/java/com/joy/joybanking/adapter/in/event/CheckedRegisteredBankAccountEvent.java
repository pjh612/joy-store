package com.joy.joybanking.adapter.in.event;

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
public class CheckedRegisteredBankAccountEvent implements OutboxEvent<String, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());
    private UUID sagaId;
    private String loadMoneyRequestId;
    private String checkRegisteredBankAccountId;
    private String moneyId;
    private String memberId;
    private String type;
    private BigDecimal amount;
    private String fromBankName;
    private String fromBankAccountNumber;
    private Instant timestamp;
    private String payload;

    public CheckedRegisteredBankAccountEvent(UUID sagaId, String loadMoneyRequestId, String checkRegisteredBankAccountId, String moneyId, String memberId, String type, BigDecimal amount, String fromBankName, String fromBankAccountNumber) {
        this.sagaId = sagaId;
        this.loadMoneyRequestId = loadMoneyRequestId;
        this.checkRegisteredBankAccountId = checkRegisteredBankAccountId;
        this.moneyId = moneyId;
        this.memberId = memberId;
        this.type= type;
        this.amount = amount;
        this.fromBankName = fromBankName;
        this.fromBankAccountNumber = fromBankAccountNumber;
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
        return this.getClass().getSimpleName();
    }

    @Override
    public String type() {
        return type;
    }

    @Override
    public Instant timestamp() {
        return this.timestamp;
    }

    @Override
    public String payload() {
        return payload;
    }
}
