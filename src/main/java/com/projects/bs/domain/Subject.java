package com.projects.bs.domain;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;

@Data
@Entity
@Table(indexes = @Index(columnList = "name", unique = true))
public class Subject implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;
}