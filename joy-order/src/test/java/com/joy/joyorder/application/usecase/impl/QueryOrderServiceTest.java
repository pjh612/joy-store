package com.joy.joyorder.application.usecase.impl;

import com.joy.joyorder.application.client.ItemClient;
import com.joy.joyorder.application.client.dto.ItemResponse;
import com.joy.joyorder.application.usecase.dto.FindOrderResponse;
import com.joy.joyorder.application.usecase.dto.QueryOrderBySellerIdRequest;
import com.joy.joyorder.domain.models.Item;
import com.joy.joyorder.domain.models.Order;
import com.joy.joyorder.domain.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class QueryOrderServiceTest {
    @InjectMocks
    QueryOrderService queryOrderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ItemClient itemClient;

    @Test
    void queryBySellerIdTest() {
        Item item = new Item(UUID.randomUUID(), "itemName", UUID.randomUUID(), BigDecimal.valueOf(2000));
        ItemResponse itemResponse = new ItemResponse(item.getId(), item.getPrice(), item.getItemName(), "description", item.getSellerId());
        UUID buyerId = UUID.randomUUID();
        Order order1 = Order.createNew(buyerId);
        order1.addOrderItem(item.getId(), item.getItemName(), item.getSellerId(), null, item.getPrice(), 2, null);
        Order order2 = Order.createNew(buyerId);
        order2.addOrderItem(item.getId(), item.getItemName(), item.getSellerId(), null, item.getPrice(), 1, null);


        QueryOrderBySellerIdRequest queryOrderBySellerIdRequest = new QueryOrderBySellerIdRequest(item.getSellerId(), null, null, null, 5);
        given(orderRepository.findBySellerId(queryOrderBySellerIdRequest.toCriteria())).willReturn(List.of(order2, order1));
        given(itemClient.findAllByIdIn(any())).willReturn(List.of(itemResponse));

        Map<UUID, ItemResponse> itemResponseMap = Stream.of(itemResponse)
                .collect(Collectors.toMap(ItemResponse::id, Function.identity()));
        List<FindOrderResponse> expect = List.of(FindOrderResponse.of(order2, itemResponseMap), FindOrderResponse.of(order1, itemResponseMap));

        //when
        List<FindOrderResponse> actual = queryOrderService.queryBySellerId(queryOrderBySellerIdRequest);

        assertThat(actual).isEqualTo(expect);
    }
}