package com.projects.bs.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class ApplicationsController {

    @GetMapping("/")
    public String getApplications(Map<String, Object> model) {
        return "index";
    }
}