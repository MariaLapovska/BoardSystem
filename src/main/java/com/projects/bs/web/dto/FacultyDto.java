package com.projects.bs.web.dto;

import com.projects.bs.domain.Faculty;
import com.projects.bs.domain.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacultyDto {

    @NotNull@Pattern(regexp = "^[a-zA-Z ,.'-]{3,50}$")
    private String name;

    @NotNull@Pattern(regexp = "^[1-4][0-9]$")
    private String plan;

    @NotNull@Size(min = 3, max = 3)
    private List<Long> subjectIds;

    @AssertTrue
    private boolean isUnique() {
        return new HashSet<>(subjectIds).size() == 3;
    }

    public Faculty toFaculty(Set<Subject> subjects) {
        return new Faculty(0L, name, Integer.valueOf(plan), true, subjects);
    }
}