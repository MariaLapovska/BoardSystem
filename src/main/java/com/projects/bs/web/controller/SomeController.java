package com.projects.bs.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by Mariia_Lapovska on 09-Mar-17.
 */
@Controller
public class SomeController {

    @GetMapping("/")
    public String lol(Map<String, Object> model) {
        return "index";
    }
}
