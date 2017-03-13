package com.projects.bs.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @GetMapping("/")
    public String getProfilePage() {
        return "/user/profile";
    }

    @GetMapping("/edit")
    public String getEditProfilePage() {
        return "/profile/editProfile";
    }

    @GetMapping("/delete")
    public String getDeleteProfilePage() {
        return "/profile/deleteProfile";
    }

    @PostMapping("/edit")
    public void editProfile() {

    }

    @PostMapping("/delete")
    public  void deleteProfile() {

    }
}
