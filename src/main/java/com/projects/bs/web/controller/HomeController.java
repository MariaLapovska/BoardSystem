package com.projects.bs.web.controller;

import com.projects.bs.domain.Application;
import com.projects.bs.domain.Faculty;
import com.projects.bs.service.ApplicationService;
import com.projects.bs.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class HomeController {

    private static final int APPLICATIONS_PER_PAGE = 20;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/")
    public String getApplications(Map<String, Object> model,
                                  @RequestParam(value = "faculty", required = false) Long facultyId,
                                  @RequestParam(value = "search", required = false) String search,
                                  @RequestParam(value = "page", required = false) Integer page) {
        model.put("faculties", facultyService.findAll());

        if (search != null) {
            model.put("applications", applicationService.findByParameter(search));
            model.put("currentPage", 1);
            model.put("noOfPages", 1);
            return "index";
        }

        page = page != null ? page : 1;
        Page<Application> applications;

        if (facultyId != null) {
            Faculty faculty = facultyService.findOne(facultyId);
            applications = applicationService.findByFaculty(faculty, page, APPLICATIONS_PER_PAGE);
            model.put("selectedFaculty", faculty);
            model.put("applications", applications);
        } else {
            applications = applicationService.findAll(page, APPLICATIONS_PER_PAGE);
            model.put("applications", applications);
        }

        model.put("currentPage", page);
        model.put("noOfPages", applications.getSize() / APPLICATIONS_PER_PAGE);

        return "index";
    }
}