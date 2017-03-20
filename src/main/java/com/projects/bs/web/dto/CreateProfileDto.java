package com.projects.bs.web.dto;

import com.projects.bs.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProfileDto {

    @NotNull@Pattern(regexp = "^[a-zA-Z.-_0-9]{5,20}$")
    private String login;

    @NotNull@Pattern(regexp = "^\\S{5,20}$")
    private String password;

    @NotNull@Pattern(regexp = "^\\S{5,20}$")
    private String confirmPassword;

    @NotNull@Pattern(regexp = "^[a-zA-Z\u0430-\u044f\u0410-\u042f'-]{3,25}$")
    private String name;

    @NotNull@Pattern(regexp = "^[a-zA-Z\u0430-\u044f\u0410-\u042f'-]{3,25}$")
    private String surname;

    @AssertTrue
    private boolean isValid() {
        return password.equals(confirmPassword);
    }

    public User toUser() {
        return new User(0L, login, password, name, surname, User.Role.USER, null);
    }
}