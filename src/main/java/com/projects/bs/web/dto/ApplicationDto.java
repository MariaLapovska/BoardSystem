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
import java.util.HashMap;
import java.util.Map;

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

    @NotNull@Min(100)@Max(200)
    private int certificateGrade;

    @NotNull@Min(1)
    private long facultyId;

    @NotNull@Valid@Size(min = 3, max = 3)
    private Map<Integer, Exam> exams;

    @AssertTrue
    private boolean isUniqueSubjects() {
        return exams.values().stream().mapToLong(Exam::getSubjectId).distinct().count() == 0;
    }

    public Application editApplication(Application application) {
        Faculty faculty = facultyService.findOne(facultyId);
        Map<Subject, Integer> subjectGrades = new HashMap<>();

        for (Exam exam : exams.values()) {
            Subject subject = subjectService.findOne(exam.getSubjectId());
            subjectGrades.put(subject, exam.getGrade());
        }

        application.setFaculty(faculty);
        application.setCertificateNumber(certificateNumber);
        application.setCertificateGrade(certificateGrade);
        application.setExams(subjectGrades);

        return application;
    }

    public Application toApplication(User user) {
        Faculty faculty = facultyService.findOne(facultyId);
        Map<Subject, Integer> subjectGrades = new HashMap<>();

        for (Exam exam : exams.values()) {
            Subject subject = subjectService.findOne(exam.getSubjectId());
            subjectGrades.put(subject, exam.getGrade());
        }

        return new Application(0L, user, faculty, certificateNumber, certificateGrade, subjectGrades, 0);
    }
}
