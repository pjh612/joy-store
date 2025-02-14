package com.joy.joymoney.application.dto;

import java.util.UUID;

public record CreateMoneyRequest(
        UUID memberId
) {
}
