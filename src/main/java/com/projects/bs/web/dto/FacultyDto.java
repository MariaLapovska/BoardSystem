package com.projects.bs.web.dto;

import com.projects.bs.domain.Faculty;
import com.projects.bs.domain.Subject;
import com.projects.bs.service.SubjectService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacultyDto {

    @Autowired
    private SubjectService subjectService;

    @Pattern(regexp = "^[a-zA-Z ,.'-]{3,50}$")
    private String name;

    @Min(10)@Max(40)
    private int plan;

    @NotNull@Size(min = 3, max = 3)
    private Set<Long> subjectIds;

    public Faculty toFaculty() {
        Set<Subject> subjects = new HashSet<>();
        subjectIds.forEach(subjectId -> subjects.add(subjectService.findOne(subjectId)));
        return new Faculty(0L, name, plan, true, subjects);
    }
}