package com.joy.joymoney.domain.model;


import com.joy.joymoney.domain.WalletBalanceException;

import java.math.BigDecimal;

public class Money {
    private String id;
    private String memberId;
    private BigDecimal balance;

    public static Money createNew(String memberId) {
        return new Money(null, memberId, BigDecimal.ZERO);
    }

    public BigDecimal load(BigDecimal amount) {
        this.balance = this.balance.add(amount);
        return this.balance;
    }

    public BigDecimal decrease(BigDecimal amount) {
        if(this.balance.compareTo(amount) < 0) {
            throw new WalletBalanceException("잔액이 부족합니다.");
        }
        this.balance = this.balance.subtract(amount);

        return this.balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getId() {
        return id;
    }

    public String getMemberId() {
        return memberId;
    }

    public Money(String id, String memberId, BigDecimal balance) {
        this.id = id;
        this.memberId = memberId;
        this.balance = balance;
    }
}
