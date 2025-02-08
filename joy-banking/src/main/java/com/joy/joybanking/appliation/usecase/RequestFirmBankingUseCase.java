package com.joy.joybanking.appliation.usecase;

import com.joy.joybanking.appliation.dto.RequestFirmBankingRequest;
import com.joy.joybanking.appliation.dto.RequestFirmBankingResponse;

public interface RequestFirmBankingUseCase {
    RequestFirmBankingResponse request(RequestFirmBankingRequest request);
}
