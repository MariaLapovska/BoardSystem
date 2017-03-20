package com.projects.bs.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import java.io.Serializable;
import java.util.Map;

@Data
@EqualsAndHashCode(exclude = "id")
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
    private String certificateNumber;

    @Column(name = "certificate_grade", nullable = false)
    private int certificateGrade;

    @ElementCollection
    @CollectionTable(name = "application_exam")
    @MapKeyJoinColumn(name = "subject_id")
    @Column(name = "exam_grade")
    private Map<Subject, Integer> exams;

    @Column(name = "total_grade", nullable = false)
    private int totalGrade;

    public int getSumGrade() {
        return certificateGrade + exams.values().stream().reduce(0, Integer::sum);
    }
}