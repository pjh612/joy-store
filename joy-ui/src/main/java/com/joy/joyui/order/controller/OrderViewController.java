package com.joy.joyui.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderViewController {
    @GetMapping("/orders")
    public String orderList() {
        return "orderlist";
    }
}
