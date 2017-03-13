package com.projects.bs.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import java.io.Serializable;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application",
       indexes = {
               @Index(columnList = "user_id", unique = true),
               @Index(columnList = "faculty_id"),
               @Index(columnList = "certificate_number", unique = true)
       })
public class Application implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

    @Column(name = "certificate_number", nullable = false, length = 10)
    @Pattern(regexp = "^[0-9]{10}$", message = "") //TODO: message
    private String certificateNumber;

    @Column(name = "certificate_grade", nullable = false)
    @Min(value = 100, message = "")@Max(value = 200, message = "") //TODO: message
    private int certificateGrade;

    @ElementCollection
    @CollectionTable(name = "application_exam")
    @MapKeyJoinColumn(name = "subject_id")
    @Column(name = "exam_grade")
    private Map<Subject, Integer> exams;

    public int getSumGrade() {
        return certificateGrade + exams.values().stream().reduce(0, Integer::sum);
    }
}