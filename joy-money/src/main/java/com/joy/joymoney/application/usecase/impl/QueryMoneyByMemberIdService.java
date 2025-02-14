package com.joy.joymoney.application.usecase.impl;

import com.joy.joymoney.application.dto.MoneyInfoResponse;
import com.joy.joymoney.application.usecase.QueryMoneyByMemberIdUseCase;
import com.joy.joymoney.domain.repository.MoneyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QueryMoneyByMemberIdService implements QueryMoneyByMemberIdUseCase {
    private final MoneyRepository moneyRepository;

    @Override
    public MoneyInfoResponse query(UUID memberId) {
        return moneyRepository.findByMemberId(memberId)
                .map(it -> new MoneyInfoResponse(it.getMemberId(), it.getBalance()))
                .orElseThrow(() -> new EntityNotFoundException("해당 회원의 조이 머니 정보가 없습니다."));
    }
}
