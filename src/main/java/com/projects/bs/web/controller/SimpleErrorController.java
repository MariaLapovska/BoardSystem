package com.projects.bs.web.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class SimpleErrorController implements ErrorController {
    private static final String PATH = "/error";

    @RequestMapping(PATH)
    public String handleError(HttpServletResponse response, Model model) {
        model.addAttribute("errorStatus", response.getStatus());
        return PATH;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}