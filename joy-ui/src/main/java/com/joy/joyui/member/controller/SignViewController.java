package com.joy.joyui.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignViewController {

    @GetMapping("/sign-in")
    public String signInView() {
        return "signin";
    }

    @GetMapping("/sign-up")
    public String signupView() {
        return "signup";
    }
}
