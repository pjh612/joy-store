package com.joy.joyadmin.item.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class ItemManageViewController {

    @GetMapping
    public String itemManageView() {
        return "itemManage";
    }
}
