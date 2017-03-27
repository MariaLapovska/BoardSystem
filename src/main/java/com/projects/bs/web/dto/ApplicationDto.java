package com.projects.bs.web.dto;

import com.projects.bs.domain.Application;
import com.projects.bs.domain.Faculty;
import com.projects.bs.domain.Subject;
import com.projects.bs.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDto {

    @NotNull@Pattern(regexp = "^[0-9]{10}$")
    private String certificateNumber;

    @NotNull@Pattern(regexp = "^[1-2][0-9]{2}$")
    private String certificateGrade;

    @NotNull@Min(1)
    private long facultyId;

    @NotNull@Valid@Size(min = 3, max = 3)
    private List<Exam> exams;

    public Application editApplication(Application application, Faculty faculty, Map<Subject, Integer> exams) {
        application.setFaculty(faculty);
        application.setCertificateNumber(certificateNumber);
        application.setCertificateGrade(Integer.valueOf(certificateGrade));
        application.setExams(exams);

        return application;
    }

    public Application toApplication(User user, Faculty faculty, Map<Subject, Integer> exams) {
        return new Application(0L, user, faculty, certificateNumber, Integer.valueOf(certificateGrade), exams, 0, Application.Status.NEW);
    }
}