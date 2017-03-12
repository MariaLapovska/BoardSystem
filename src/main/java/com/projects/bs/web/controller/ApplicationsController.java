package com.projects.bs.web.controller;

import com.projects.bs.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ApplicationsController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping("/")
    public String getApplications(Map<String, Object> model,
                                  @RequestParam(value = "faculty", required = false) Long facultyId,
                                  @RequestParam(value = "search", required = false) String search,
                                  @RequestParam(value = "page", required = false) Long page,
                                  @RequestParam(value = "field", required = false) String field,
                                  @RequestParam(value = "order", required = false) String order) {
        model.put("faculties", facultyService.findAll());
        if (facultyId != null) {
            model.put("selectedFaculty", facultyService.findOne(facultyId));
        }
        //model.put("applications", new ArrayList<>());
        return "index";
    }
}