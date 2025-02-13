package com.joy.joyad.order.controller;

import com.joy.joyad.order.dto.OrderSummaryResponse;
import com.joy.joyad.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class OrderSummaryController {
    private final OrderService orderService;

    @GetMapping
    public ModelAndView getOrderSummary(
            @RegisteredOAuth2AuthorizedClient("joy-ad-authorization-code") OAuth2AuthorizedClient authorizedClient,
            ModelAndView modelAndView
    ) {
        OrderSummaryResponse summary = orderService.getOrderSummary(authorizedClient);
        modelAndView.addObject("orderCount", summary.orderCount())
                .addObject("orderTotalAmount", summary.totalAmount())
                .setViewName("index");

        return modelAndView;
    }
}
