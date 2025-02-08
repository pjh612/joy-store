package com.joy.joybanking.adapter.out.persistence.jpa.entity;

import com.joy.joycommon.hibernate.annotations.UuidV7Generator;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@Table(name = "registered_bank_account")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisteredAccountJpaEntity {
    @Id
    @UuidV7Generator
    private String id;
    private String memberId;
    private String bankName;
    private String accountNumber;
    private boolean isValid;
}
