package com.joy.joymoney.application.usecase;

import java.math.BigDecimal;

public interface LoadMoneyUseCase {
    void loadMoneySaga(String memberId, BigDecimal request);
}
