package com.projects.bs.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/faculty")
public class FacultiesController {

    @GetMapping("/edit")
    public String getEditApplicationPage(){
        return "/user/";
    }

    @GetMapping("/delete")
    public void getDeleteApplicationPage() {}

    @PostMapping("/edit")
    public void editApplication() {}

    @PostMapping("/delete")
    public void deleteApplication() {}
}
