package com.joy.joymoney.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class MoneyChangingRequest {
    private String id;
    private String targetMemberId;

    private ChangingType changingType;
    private BigDecimal amount;



    private ChangingStatus status;
    private UUID uuid;
    private Instant createdAt;

    public static MoneyChangingRequest createNew(String targetMemberId,
                                                 ChangingType changingType, BigDecimal amount) {
        return new MoneyChangingRequest(
                null,
                targetMemberId,
                changingType,
                amount,
                ChangingStatus.REQUESTED,
                null,
                Instant.now()
        );
    }

    public static MoneyChangingRequest createNew(String id, String targetMemberId,
                                                 ChangingType changingType, BigDecimal amount) {
        return new MoneyChangingRequest(
                id,
                targetMemberId,
                changingType,
                amount,
                ChangingStatus.REQUESTED,
                null,
                Instant.now()
        );
    }

    public MoneyChangingRequest setStatus(ChangingStatus status) {
        this.status = status;

        return this;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void success() {
        this.status = ChangingStatus.SUCCEEDED;
    }

    public void fail() {
        this.status = ChangingStatus.FAILED;
    }
}
