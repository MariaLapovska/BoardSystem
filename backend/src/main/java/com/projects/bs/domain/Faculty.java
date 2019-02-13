package com.projects.bs.domain;

import lombok.*;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = "id")
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
    private String name;

    @Column(name = "recruitment_plan", nullable = false)
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