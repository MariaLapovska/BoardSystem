package com.projects.bs.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@EqualsAndHashCode(exclude = "grade")
@NoArgsConstructor
@AllArgsConstructor
public class Exam {

    @NotNull@Min(1)
    private Long subjectId;

    @NotNull@Pattern(regexp = "^[1-2][0-9]{2}$")
    private String grade;
}
