package com.projects.bs.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", indexes = {
        @Index(columnList = "login", unique = true),
        @Index(columnList = "application_id", unique = true)
})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "login", nullable = false, length = 20)
    @Pattern(regexp = "^\\S{5,20}$", message = "")//TODO: error message
    private String login;

    @Column(name = "password", nullable = false, length = 60)
    //@Pattern(regexp = "^\\S{5,20}$", message = "")//TODO: error message
    private String password;

    @Transient
    private String confirmPassword;

    @Column(name = "name", nullable = false, length = 25)
    @Pattern(regexp = "^[a-zA-Z ,.'-]{3,25}$", message = "")//TODO: error message
    private String name;

    @Column(name = "surname", nullable = false, length = 25)
    @Pattern(regexp = "^[a-zA-Z ,.'-]{3,25}$", message = "")//TODO: error message
    private String surname;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    @JoinColumn(name = "application_id")
    private Application application;

    public enum Role {
        ROLE_USER, ROLE_ADMIN
    }
}