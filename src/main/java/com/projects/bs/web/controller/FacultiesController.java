package com.projects.bs.web.controller;

import com.projects.bs.domain.Application;
import com.projects.bs.domain.Faculty;
import com.projects.bs.service.ApplicationService;
import com.projects.bs.service.FacultyService;
import com.projects.bs.service.SubjectService;
import com.projects.bs.service.UserService;
import com.projects.bs.web.dto.FacultyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/faculty")
public class FacultiesController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/add")
    public String getAddFacultyPage(Model model) {
        model.addAttribute("subjects", subjectService.findAll());
        return "/admin/addFaculty";
    }

    @PostMapping("/add")
    public String addFaculty(@ModelAttribute("facultyForm") @Valid FacultyDto facultyForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "wrongInput");
            model.addAttribute("subjects", subjectService.findAll());
            return "/admin/addFaculty";
        } else if (facultyService.findByName(facultyForm.getName()) != null) {
            model.addAttribute("error", "nameTaken");
            model.addAttribute("subjects", subjectService.findAll());
            return "/admin/addFaculty";
        } else {
            Faculty faculty= facultyForm.toFaculty();
            faculty = facultyService.saveFaculty(faculty);
            String message = "changesSuccess";
            if (faculty == null || faculty.getId() == 0) {
                message = "changesError";
            }
            return "redirect:/profile?message=" + message;
        }
    }

    @PostMapping("/close")
    public String closeFaculty(@ModelAttribute("facultyId") long facultyId, Model model) {
        Faculty faculty = facultyService.findOne(facultyId);
        faculty.setAvailable(false);
        faculty = facultyService.saveFaculty(faculty);
        String message = "changesSuccess";
        if (faculty == null || faculty.getId() == 0) {
            message = "changesError";
        } else {
            List<Application> applications = applicationService.findByFaculty(faculty);
            int accepted = applications.size() < faculty.getRecruitmentPlan() ? applications.size() : faculty.getRecruitmentPlan();
            for (Application application : applications.subList(0, accepted)) {
                application.setStatus(Application.Status.ACCEPTED);
                applicationService.saveApplication(application);
            }
            if (accepted > applications.size()) {
                for (Application application : applications.subList(accepted, applications.size())) {
                    application.setStatus(Application.Status.DECLINED);
                    applicationService.saveApplication(application);
                }
            }
            model.addAttribute("faculty", faculty);
            model.addAttribute("applications", applications);
            model.addAttribute("total", applications.size());
            model.addAttribute("accepted", accepted);
        }
        return "/admin/closeFaculty?message=" + message;
    }

    @GetMapping(value = "/download", produces = "")
    public void downloadFaculty(@RequestParam(value = "faculty") long facultyId) {
        Faculty faculty = facultyService.findOne(facultyId);

    }
}
