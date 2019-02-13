package com.projects.bs.domain;

import lombok.*;

import java.io.Serializable;
import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subject", indexes = @Index(columnList = "name", unique = true))
public class Subject implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;
}