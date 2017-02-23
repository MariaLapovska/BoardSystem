package com.projects.bs.domain;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Data
public class Subject implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;
}