package com.joy.joyadmin.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderManageViewController {

    @GetMapping
    public String orderManageView() {
        return "orderManage";
    }
}
