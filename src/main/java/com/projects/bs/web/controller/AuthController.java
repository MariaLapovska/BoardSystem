package com.projects.bs.web.controller;

import com.projects.bs.domain.User;
import com.projects.bs.service.UserService;
import com.projects.bs.service.auth.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @GetMapping("login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("signup")
    public String signUpPage(Model model) {
        model.addAttribute("userForm", new User());
        return "auth/signup";
    }

    @PostMapping("signup")
    public String signUp(@ModelAttribute("userForm") User userForm, Model model) {
        userService.save(userForm);

        securityService.autologin(userForm.getEmail(), userForm.getConfirmPassword());
        return "user/profile";
    }
}
