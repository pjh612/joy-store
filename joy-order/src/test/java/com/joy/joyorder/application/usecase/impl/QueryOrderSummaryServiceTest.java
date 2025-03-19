package com.joy.joyorder.application.usecase.impl;

import com.fasterxml.uuid.Generators;
import com.joy.joyorder.application.repository.OrderSummaryRepository;
import com.joy.joyorder.application.usecase.dto.OrderSummaryResponse;
import com.joy.joyorder.domain.models.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class QueryOrderSummaryServiceTest {

    @InjectMocks
    private QueryOrderSummaryService queryOrderSummaryService;

    @Mock
    private OrderSummaryRepository orderSummaryRepository;

    @Test
    void queryOrderSummaryTest() {
        //given
        UUID sellerId = Generators.timeBasedEpochGenerator().generate();
        OrderSummaryResponse orderSummaryResponse = new OrderSummaryResponse(1, BigDecimal.valueOf(1000));
        given(orderSummaryRepository.findOrderSummaryBySellerId(sellerId)).willReturn(orderSummaryResponse);

        //when
        OrderSummaryResponse query = queryOrderSummaryService.query(sellerId);

        //then
        verify(orderSummaryRepository).findOrderSummaryBySellerId(sellerId);

        Assertions.assertEquals(orderSummaryResponse, query);
    }
}