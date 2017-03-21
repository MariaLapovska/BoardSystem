package com.projects.bs.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(exclude = "grade")
@NoArgsConstructor
@AllArgsConstructor
public class Exam {

    @NotNull@Min(1)
    private Long subjectId;

    @NotNull@Min(100)@Max(200)
    private int grade;
}
