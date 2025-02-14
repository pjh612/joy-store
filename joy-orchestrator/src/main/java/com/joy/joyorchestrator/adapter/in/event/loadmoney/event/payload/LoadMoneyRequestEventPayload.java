package com.joy.joyorchestrator.adapter.in.event.loadmoney.event.payload;

import com.joy.joyorchestrator.domain.model.PayloadType;
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
    private PayloadType type;
}
