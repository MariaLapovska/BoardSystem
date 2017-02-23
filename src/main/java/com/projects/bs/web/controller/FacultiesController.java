package com.projects.bs.web.controller;

import com.projects.bs.domain.Faculty;
import com.projects.bs.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/faculties")
public class FacultiesController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping
    public List<Faculty> getFaculties() {
        return facultyService.findAll();
    }
}
