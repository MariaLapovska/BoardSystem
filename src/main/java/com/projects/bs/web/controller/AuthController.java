package com.projects.bs.web.controller;

import com.projects.bs.domain.User;
import com.projects.bs.service.UserService;
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
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

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
        autoLogin(user);

        return "redirect:/user/profile";
    }

    private boolean autoLogin(User user) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getLogin());
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
                    UsernamePasswordAuthenticationToken(userDetails, user.getConfirmPassword(), userDetails
                    .getAuthorities());
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            if (usernamePasswordAuthenticationToken.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}