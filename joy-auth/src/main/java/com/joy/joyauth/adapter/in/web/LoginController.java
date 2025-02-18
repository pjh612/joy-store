package com.joy.joyauth.adapter.in.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/seller/login")
    String login(Model model) {
        model.addAttribute("loginProcessingUrl", "/seller/login");
        return "login";
    }

    @GetMapping("/member/login")
    String memberLogin(Model model, @RequestParam(required = false, name = "redirect_uri") String redirectUri) {
        model.addAttribute("loginProcessingUrl", "/member/login");
        model.addAttribute("redirectUri", redirectUri);
        return "login";
    }
}
