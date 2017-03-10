package com.projects.bs.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auth")
public class AuthController {

    @GetMapping("login")
    public String loginPage() {
        return "auth/login";
    }

    @PostMapping("login")
    public String login() {
        return "user/profile";
    }

    @GetMapping("signup")
    public String signUpPage() {
        return "auth/signup";
    }

    @PostMapping("signup")
    public String signUp() {
        return "user/profile";
    }
}
