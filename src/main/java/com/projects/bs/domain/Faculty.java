package com.projects.bs.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "faculty", indexes = @Index(columnList = "name", unique = true))
public class Faculty implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false, length = 50)
    @Pattern(regexp = "^[a-zA-Z ,.'-]{3,50}$", message = "")//TODO: error message
    private String name;

    @Column(name = "passing_score", nullable = false)
    @Min(value = 600, message = "")@Max(value = 800, message = "")//TODO: error message
    private int passingScore;

    @Column(name = "recruitment_plan", nullable = false)
    @Min(value = 10, message = "")@Max(value = 40, message = "")//TODO: error message
    private int recruitmentPlan;

    @Column(name = "is_available", nullable = false)
    private boolean isAvailable;

    @ManyToMany
    @JoinTable(
            name = "faculty_subject",
            joinColumns = @JoinColumn(name = "faculty_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id"))
    private Set<Subject> subjects;
}