package com.joy.joybanking.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FirmBankingRequest {
    private String id;
    private String fromBankName;
    private String fromBankAccountNumber;
    private String toBankName;
    private String toBankAccountNumber;
    private BigDecimal amount;
    private FirmBankingStatus status;
    private UUID uuid;

    public static FirmBankingRequest createNew(
            String fromBankName,
            String fromBankAccountNumber,
            String toBankName,
            String toBankAccountNumber,
            BigDecimal amount
    ) {
        return FirmBankingRequest.createNew(null, fromBankName, fromBankAccountNumber, toBankName, toBankAccountNumber, amount);
    }

    public static FirmBankingRequest createNew(
            UUID id,
            String fromBankName,
            String fromBankAccountNumber,
            String toBankName,
            String toBankAccountNumber,
            BigDecimal amount
    ) {
        return new FirmBankingRequest(id.toString(), fromBankName, fromBankAccountNumber, toBankName, toBankAccountNumber, amount, FirmBankingStatus.REQUESTED, null);
    }

    public void success() {
        this.status = FirmBankingStatus.SUCCEEDED;
    }

    public void fail() {
        this.status = FirmBankingStatus.FAILED;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
