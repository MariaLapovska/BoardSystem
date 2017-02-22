package com.projects.bs.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
public class Faculty implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "info")
    private String info;

    @Column(name = "passing_score")
    private int passingScore;

    @Column(name = "recruitment_plan")
    private int recruitmentPlan;

    @ManyToMany
    @JoinTable(
            name = "faculty_subject",
            joinColumns = @JoinColumn(name = "faculty_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id"))
    private Set<Subject> subjects;
}