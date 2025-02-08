package com.joy.joybanking.appliation.dto;

public record RegisterAccountRequest(
        String memberId,
        String bankName,
        String bankAccountNumber,
        String isValid
) {
}
