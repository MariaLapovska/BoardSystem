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
public class EditProfileDto {

    @NotNull@Pattern(regexp = "^\\S{5,20}$")
    private String oldPassword;

    @NotNull@Pattern(regexp = "^\\S{5,20}$")
    private String newPassword;

    @NotNull@Pattern(regexp = "^\\S{5,20}$")
    private String confirmPassword;

    @NotNull@Pattern(regexp = "^[a-zA-Z\u0430-\u044f\u0410-\u042f'-]{3,25}$")
    private String name;

    @NotNull@Pattern(regexp = "^[a-zA-Z\u0430-\u044f\u0410-\u042f'-]{3,25}$")
    private String surname;

    @AssertTrue
    private boolean isValid() {
        return newPassword.equals(confirmPassword);
    }

    public User editUser(User user) {
        user.setName(name);
        user.setSurname(surname);
        user.setPassword(newPassword);
        return user;
    }
}