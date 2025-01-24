package com.joy.joyapi.order.application.usecase.event;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaceOrderEvent {
    private List<Long> sellerSequenceList;
    private Long orderSequence;
}
