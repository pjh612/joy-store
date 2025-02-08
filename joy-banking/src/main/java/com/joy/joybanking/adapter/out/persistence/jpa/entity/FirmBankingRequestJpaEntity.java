package com.joy.joybanking.adapter.out.persistence.jpa.entity;

import com.joy.joybanking.domain.model.FirmBankingStatus;
import com.joy.joycommon.hibernate.annotations.UuidV7Generator;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Entity
@AllArgsConstructor
@Table(name = "firm_banking_request")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FirmBankingRequestJpaEntity {
    @Id
    @UuidV7Generator
    private String id;
    private String fromBankName;
    private String fromBankAccountNumber;
    private String toBankName;
    private String toBankAccountNumber;
    private BigDecimal amount;
    private FirmBankingStatus status;
    private String uuid;
}
