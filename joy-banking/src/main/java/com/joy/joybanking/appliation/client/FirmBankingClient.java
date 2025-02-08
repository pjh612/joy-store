package com.joy.joybanking.appliation.client;

import com.joy.joybanking.appliation.client.dto.ExternalFirmBankingRequest;
import com.joy.joybanking.appliation.client.dto.ExternalFirmBankingResponse;

public interface FirmBankingClient {
    ExternalFirmBankingResponse requestFirmBanking(ExternalFirmBankingRequest request);
}
