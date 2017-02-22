package com.projects.bs.domain;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Subject implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private long id;

    @Column(name = "name")
    private String name;
}
