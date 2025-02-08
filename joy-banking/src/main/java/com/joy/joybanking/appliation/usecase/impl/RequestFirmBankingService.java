package com.joy.joybanking.appliation.usecase.impl;

import com.fasterxml.uuid.Generators;
import com.joy.joybanking.appliation.client.FirmBankingClient;
import com.joy.joybanking.appliation.client.dto.ExternalFirmBankingRequest;
import com.joy.joybanking.appliation.client.dto.ExternalFirmBankingResponse;
import com.joy.joybanking.appliation.dto.RequestFirmBankingRequest;
import com.joy.joybanking.appliation.dto.RequestFirmBankingResponse;
import com.joy.joybanking.appliation.usecase.RequestFirmBankingUseCase;
import com.joy.joybanking.domain.model.FirmBankingRequest;
import com.joy.joybanking.domain.repository.FirmBankingRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RequestFirmBankingService implements RequestFirmBankingUseCase {
    private final FirmBankingClient firmBankingClient;
    private final FirmBankingRequestRepository firmBankingRequestRepository;

    @Override
    @Transactional
    public RequestFirmBankingResponse request(RequestFirmBankingRequest request) {
        FirmBankingRequest newFirmBankingRequest = FirmBankingRequest.createNew(
                Generators.timeBasedEpochGenerator().generate(),
                request.fromBankName(),
                request.fromBankAccountNumber(),
                request.toBankName(),
                request.toBankAccountNumber(),
                request.amount()
        );
        FirmBankingRequest firmBankingRequest = firmBankingRequestRepository.save(newFirmBankingRequest);

        ExternalFirmBankingResponse externalFirmBankingResponse = firmBankingClient.requestFirmBanking(new ExternalFirmBankingRequest(
                request.fromBankName(),
                request.fromBankAccountNumber(),
                request.toBankName(),
                request.toBankAccountNumber(),
                request.amount()
        ));

        UUID uuid = Generators.timeBasedEpochGenerator().generate();
        firmBankingRequest.setUuid(uuid);

        if (externalFirmBankingResponse.resultCode() == 1) {
            firmBankingRequest.success();
        } else {
            firmBankingRequest.fail();
        }

        firmBankingRequestRepository.save(firmBankingRequest);

        return new RequestFirmBankingResponse(firmBankingRequest.getId(), firmBankingRequest.getStatus().name(), uuid);
    }
}
