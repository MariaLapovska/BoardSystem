package com.projects.bs.web.validation;

import org.springframework.validation.*;

public class ValidationErrorBuilder {

    public static ValidationError fromBindingErrors(Errors errors) {
        ValidationError error = new ValidationError(errors.getErrorCount());
        errors.getAllErrors().forEach(objectError -> error.addValidationError(objectError.getDefaultMessage()));
        return error;
    }
}