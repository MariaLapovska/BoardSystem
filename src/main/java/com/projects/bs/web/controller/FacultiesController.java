package com.projects.bs.web.controller;

import com.projects.bs.domain.Application;
import com.projects.bs.domain.Faculty;
import com.projects.bs.domain.Subject;
import com.projects.bs.service.ApplicationService;
import com.projects.bs.service.FacultyService;
import com.projects.bs.service.SubjectService;
import com.projects.bs.web.dto.FacultyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            Set<Subject> subjects = new HashSet<>();
            for (Long subjectId : facultyForm.getSubjectIds()) {
                Subject subject = subjectService.findOne(subjectId);
                if (subject == null) {
                    model.addAttribute("error", "subjectsInvalid");
                    model.addAttribute("subjects", subjectService.findAll());
                    return "/admin/addFaculty";
                } else {
                    subjects.add(subject);
                }
            }
            Faculty faculty = facultyForm.toFaculty(subjects);
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
            if (accepted < applications.size()) {
                for (Application application : applications.subList(accepted, applications.size())) {
                    application.setStatus(Application.Status.DECLINED);
                    applicationService.saveApplication(application);
                }
            }
            model.addAttribute("faculty", faculty);
            model.addAttribute("applications", applications.subList(0, accepted));
            model.addAttribute("total", applications.size());
            model.addAttribute("accepted", accepted);
        }
        model.addAttribute("message", message);
        return "/admin/closeFaculty";
    }

    @GetMapping(value = "/download")
    public void downloadFaculty(@RequestParam(value = "faculty") long facultyId, HttpServletResponse response) throws IOException {
        Faculty faculty = facultyService.findOne(facultyId);
        List<Application> applications = applicationService.findByFaculty(faculty, 1, faculty.getRecruitmentPlan()).getContent();

        response.setContentType("text/csv");
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", "applications.csv");
        response.setHeader(headerKey, headerValue);
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
        String[] header = {"id", "Name", "Surname", "CertificateNumber", "CertificateGrade", "ExamGrades", "TotalGrade"};

        csvWriter.writeHeader(header);

        for (Application application : applications) {
            csvWriter.write(application, header);
        }

        csvWriter.close();
    }
}
