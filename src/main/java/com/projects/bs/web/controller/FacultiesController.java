package com.projects.bs.web.controller;

import com.projects.bs.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/faculty")
public class FacultiesController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/add")
    public String getAddFacultyPage(Model model) {
        model.addAttribute("subjects", subjectService.findAll());
        return "/admin/addFaculty";
    }

    @PostMapping("/add")
    public void addFaculty() {}

    @PostMapping("/close")
    public void closeFaculty() {}
}
