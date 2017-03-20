package com.projects.bs.web.controller;

import com.projects.bs.domain.User;
import com.projects.bs.service.UserService;
import com.projects.bs.service.auth.SecurityService;
import com.projects.bs.web.dto.CreateProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/login")
    public String getLoginPage(HttpServletRequest request) {
        if (request.getUserPrincipal() != null) {
            return "redirect:/";
        } else {
            return "/auth/login";
        }
    }

    @GetMapping("/signup")
    public String getSignUpPage(HttpServletRequest request, Model model) {
        if (request.getUserPrincipal() != null) {
            return "redirect:/";
        } else {
            model.addAttribute("signUpForm", new CreateProfileDto());
            return "/auth/signup";
        }
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute("signUpForm") @Valid CreateProfileDto signUpForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "wrongInput");
            model.addAttribute("signUpForm", new CreateProfileDto());
            return "/auth/signup";
        } else if (userService.findByLogin(signUpForm.getLogin()) != null) {
            model.addAttribute("error", "loginTaken");
            model.addAttribute("signUpForm", new CreateProfileDto());
            return "/auth/signup";
        } else {
            User user = userService.saveUser(signUpForm.toUser());
            securityService.autoLogin(user, signUpForm.getConfirmPassword());
            return "redirect:/";
        }
    }
}