package com.projects.bs.web.controller;

import com.projects.bs.domain.Application;
import com.projects.bs.domain.Faculty;
import com.projects.bs.domain.Subject;
import com.projects.bs.domain.User;
import com.projects.bs.service.ApplicationService;
import com.projects.bs.service.FacultyService;
import com.projects.bs.service.SubjectService;
import com.projects.bs.service.UserService;
import com.projects.bs.web.dto.ApplicationDto;
import com.projects.bs.web.dto.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Controller
@RequestMapping("/application")
public class ApplicationsController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/add")
    public String getAddApplicationPage(Principal principal, Model model) {
        User currentUser = userService.findByLogin(principal.getName());
        Application application = applicationService.findByUser(currentUser);
        if (application != null && application.getStatus() == Application.Status.ACCEPTED) {
            return "redirect:/profile";
        } else if (application != null && application.getStatus() == Application.Status.NEW) {
            return "redirect:/application/edit";
        } else {
            model.addAttribute("action", "add");
            return prepareEditApplicationPage(model);
        }
    }

    @GetMapping("/edit")
    public String getEditApplicationPage(Principal principal, Model model){
        User currentUser = userService.findByLogin(principal.getName());
        Application application = applicationService.findByUser(currentUser);
        if (application == null || application.getStatus() == Application.Status.DECLINED) {
            return "redirect:/application/add";
        } else if(application.getStatus() == Application.Status.ACCEPTED) {
            return "redirect:/profile";
        } else {
            model.addAttribute("action", "edit");
            model.addAttribute("application", application);
            return prepareEditApplicationPage(model);
        }
    }

    @GetMapping("/delete")
    public String getDeleteApplicationPage(Principal principal) {
        User currentUser = userService.findByLogin(principal.getName());
        Application application = applicationService.findByUser(currentUser);
        if (application == null || application.getStatus() == Application.Status.DECLINED) {
            return "redirect:/application/add";
        } else if(application.getStatus() == Application.Status.ACCEPTED) {
            return "redirect:/profile";
        } else {
            return "/user/deleteApplication";
        }
    }

    @PostMapping("/add")
    public String addApplication(@ModelAttribute("applicationForm") @Valid ApplicationDto applicationForm, BindingResult result, Principal principal, Model model) {
        User currentUser = userService.findByLogin(principal.getName());
        if (result.hasErrors()) {
            model.addAttribute("error", "wrongInput");
            model.addAttribute("action", "add");
            return prepareEditApplicationPage(model);
        } else if (applicationService.findByParameter(applicationForm.getCertificateNumber()) != null) {
            model.addAttribute("error", "certificateTaken");
            model.addAttribute("action", "add");
            return prepareEditApplicationPage(model);
        } else {
            Faculty faculty = facultyService.findOne(applicationForm.getFacultyId());
            if (faculty == null || !faculty.isAvailable()) {
                model.addAttribute("error", "facultyInvalid");
                model.addAttribute("action", "add");
                return prepareEditApplicationPage(model);
            } else if (!isCorrectSubjects(faculty, applicationForm.getExams())) {
                model.addAttribute("error", "examsInvalid");
                model.addAttribute("action", "add");
                return prepareEditApplicationPage(model);
            }
            Map <Subject, Integer> exams = toExams(applicationForm.getExams());
            if (exams == null) {
                model.addAttribute("error", "subjectsInvalid");
                model.addAttribute("action", "add");
                return prepareEditApplicationPage(model);
            }
            Application application = applicationForm.toApplication(currentUser, faculty, exams);
            Application oldApplication = applicationService.findByUser(currentUser);
            if (oldApplication != null) {
                applicationService.delete(oldApplication.getId());
            }
            application = applicationService.saveApplication(application);
            String message = "changesSuccess";
            if (application == null || application.getId() == 0) {
                message = "changesError";
            }
            return "redirect:/profile?message=" + message;
        }
    }

    @PostMapping("/edit")
    public String editApplication(@ModelAttribute("applicationForm") @Valid ApplicationDto applicationForm, BindingResult result, Principal principal, Model model) {
        User currentUser = userService.findByLogin(principal.getName());
        Application application = applicationService.findByCertificate(applicationForm.getCertificateNumber());
        if (result.hasErrors()) {
            model.addAttribute("error", "wrongInput");
            model.addAttribute("action", "edit");
            model.addAttribute("application", application);
            return prepareEditApplicationPage(model);
        }
        if (application != null && application.getUser() != currentUser) {
            model.addAttribute("error", "certificateTaken");
            model.addAttribute("action", "edit");
            model.addAttribute("application", application);
            return prepareEditApplicationPage(model);
        } else {
            Faculty faculty = facultyService.findOne(applicationForm.getFacultyId());
            if (faculty == null || !faculty.isAvailable()) {
                model.addAttribute("error", "facultyInvalid");
                model.addAttribute("action", "edit");
                model.addAttribute("application", application);
                return prepareEditApplicationPage(model);
            } else if (!isCorrectSubjects(faculty, applicationForm.getExams())) {
                model.addAttribute("error", "examsInvalid");
                model.addAttribute("action", "edit");
                model.addAttribute("application", application);
                return prepareEditApplicationPage(model);
            }
            Map <Subject, Integer> exams = toExams(applicationForm.getExams());
            if (exams == null) {
                model.addAttribute("error", "subjectsInvalid");
                model.addAttribute("action", "edit");
                model.addAttribute("application", application);
                return prepareEditApplicationPage(model);
            }
            application = applicationForm.editApplication(application, faculty, exams);
            application = applicationService.saveApplication(application);
            String message = "changesSuccess";
            if (application == null || application.getId() == 0) {
                message = "changesError";
            }
            return "redirect:/profile?message=" + message;
        }
    }

    @PostMapping("/delete")
    public String deleteApplication(Principal principal, Model model) {
        User currentUser = userService.findByLogin(principal.getName());
        Application application = applicationService.findByUser(currentUser);
        applicationService.delete(application.getId());
        String message = "changesSuccess";
        if (applicationService.findByUser(currentUser) != null) {
            message = "changesError";
        }
        return "redirect:/profile?message=" + message;
    }

    private String prepareEditApplicationPage(Model model) {
        model.addAttribute("applicationForm", new ApplicationDto());
        model.addAttribute("faculties", facultyService.findAll());
        model.addAttribute("subjects", subjectService.findAll());
        return "/user/editApplication";
    }

    private boolean isCorrectSubjects(Faculty faculty, List<Exam> exams) {
        Set<Long> expectedSubjects = faculty.getSubjects().stream().map(Subject::getId).collect(toSet());
        return exams.stream().map(Exam::getSubjectId).collect(toSet()).equals(expectedSubjects);
    }

    private Map<Subject, Integer> toExams(List<Exam> exams) {
        Map<Subject, Integer> subjectGrades = new HashMap<>();
        for (Exam exam : exams) {
            Subject subject = subjectService.findOne(exam.getSubjectId());
            if (subject == null) {
                return null;
            } else {
                subjectGrades.put(subject, Integer.valueOf(exam.getGrade()));
            }
        }
        return subjectGrades;
    }
}