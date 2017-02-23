package com.projects.bs.web.validation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class ValidationError {

    //@JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> errors = new ArrayList<>();

    private final int errorCount;

    public void addValidationError(String error) {
        errors.add(error);
    }
}
