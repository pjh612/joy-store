package com.joy.joyorchestrator.adapter.in.event.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadMoneyRequestEventPayload {
    private String loadMoneyRequestId;
    private String memberId;
    private BigDecimal amount;
    private String status;
}
