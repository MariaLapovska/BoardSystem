package com.projects.bs.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", indexes = @Index(columnList = "email", unique = true))
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "email", nullable = false, length = 20)
    @Pattern(regexp = "^\\S{5,20}$", message = "")//TODO: error message
    private String email;

    @Column(name = "password", nullable = false)
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

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;
}