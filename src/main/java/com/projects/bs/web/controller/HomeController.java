package com.projects.bs.web.controller;

import com.projects.bs.domain.Application;
import com.projects.bs.domain.Faculty;
import com.projects.bs.service.ApplicationService;
import com.projects.bs.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private static final int APPLICATIONS_PER_PAGE = 10;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/")
    public String getApplications(Model model,
                                  @RequestParam(value = "faculty", required = false) Long facultyId,
                                  @RequestParam(value = "search", required = false) String search,
                                  @RequestParam(value = "page", required = false) Integer page) {
        model.addAttribute("faculties", facultyService.findAll());

        if (search != null && !search.isEmpty()) {
            model.addAttribute("applications", applicationService.findByParameter(search));
            model.addAttribute("currentPage", 1);
            model.addAttribute("noOfPages", 1);
            return "index";
        }

        page = page != null ? page : 1;
        Page<Application> applications;

        if (facultyId != null) {
            Faculty faculty = facultyService.findOne(facultyId);
            applications = applicationService.findByFaculty(faculty, page, APPLICATIONS_PER_PAGE);
            model.addAttribute("selectedFaculty", faculty);
            model.addAttribute("applications", applications.getContent());
        } else {
            applications = applicationService.findAll(page, APPLICATIONS_PER_PAGE);
            model.addAttribute("applications", applications.getContent());
        }

        model.addAttribute("currentPage", page);
        model.addAttribute("noOfPages", applications.getTotalPages());

        return "index";
    }
}