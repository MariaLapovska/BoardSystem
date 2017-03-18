package com.projects.bs.web.controller;

import com.projects.bs.domain.User;
import com.projects.bs.service.UserService;
import com.projects.bs.service.auth.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "/auth/login";
    }

    @GetMapping("/signup")
    public String getSignUpPage(Model model) {
        model.addAttribute("userForm", new User());
        return "/auth/signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute("userForm") User userForm) {
        User user = userService.saveUser(userForm);
        securityService.autoLogin(user);

        return "redirect:/";
    }
}