package com.projects.bs.web.dto;

import com.projects.bs.domain.Application;
import com.projects.bs.domain.Faculty;
import com.projects.bs.domain.Subject;
import com.projects.bs.domain.User;
import com.projects.bs.service.ApplicationService;
import com.projects.bs.service.FacultyService;
import com.projects.bs.service.SubjectService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;
import static java.util.stream.Collectors.toSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDto {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private SubjectService subjectService;

    @NotNull@Pattern(regexp = "^[0-9]{10}$")
    private String certificateNumber;

    @NotNull@Pattern(regexp = "^[1-2][0-9]{2}$")
    private String certificateGrade;

    @NotNull@Min(1)
    private long facultyId;

    @NotNull@Valid@Size(min = 3, max = 3)
    private List<Exam> exams;

    @AssertTrue
    private boolean isCorrectSubjects() {
        Faculty faculty = facultyService.findOne(facultyId);
        Set<Long> expectedSubjects = faculty.getSubjects().stream().map(Subject::getId).collect(toSet());
        return exams.stream().map(Exam::getSubjectId).collect(toSet()).equals(expectedSubjects);
    }

    @AssertTrue
    private boolean isAvailable() {
        return facultyService.findOne(facultyId).isAvailable();
    }

    public Application editApplication(Application application) {
        Faculty faculty = facultyService.findOne(facultyId);
        Map<Subject, Integer> subjectGrades = new HashMap<>();
        exams.forEach(exam -> subjectGrades.put(subjectService.findOne(exam.getSubjectId()), Integer.valueOf(exam.getGrade())));
        application.setFaculty(faculty);
        application.setCertificateNumber(certificateNumber);
        application.setCertificateGrade(Integer.valueOf(certificateGrade));
        application.setExams(subjectGrades);

        return application;
    }

    public Application toApplication(User user) {
        Faculty faculty = facultyService.findOne(facultyId);
        Map<Subject, Integer> subjectGrades = new HashMap<>();
        exams.forEach(exam -> subjectGrades.put(subjectService.findOne(exam.getSubjectId()), Integer.valueOf(exam.getGrade())));

        return new Application(0L, user, faculty, certificateNumber, Integer.valueOf(certificateGrade), subjectGrades, 0, Application.Status.NEW);
    }
}