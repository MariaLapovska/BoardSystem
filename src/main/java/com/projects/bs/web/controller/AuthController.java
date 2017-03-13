package com.projects.bs.web.controller;

import com.projects.bs.domain.User;
import com.projects.bs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("login")
    public String loginPage() {
        return "/auth/login";
    }

    @GetMapping("signup")
    public String signUpPage(Model model) {
        model.addAttribute("userForm", new User());
        return "/auth/signup";
    }

    @PostMapping("signup")
    public String signUp(@ModelAttribute("userForm") User userForm, HttpServletRequest request, HttpServletResponse response) throws ServletException {
        User user = userService.saveUser(userForm);
        autologin(user.getLogin(), user.getPassword());

        return "/user/profile";
    }

    private boolean authenticateUserAndInitializeSessionByUsername(String username, UserDetailsService userDetailsService, HttpServletRequest request)
    {
        boolean result = true;

        try
        {
            // generate session if one doesn't exist
            request.getSession();

            // Authenticate the user
            UserDetails user = userDetailsService.loadUserByUsername(username);
            Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());

            result = false;
        }

        return result;
    }

    private void autologin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }
}