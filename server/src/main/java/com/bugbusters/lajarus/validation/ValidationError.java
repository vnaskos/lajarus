package com.bugbusters.lajarus.validation;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vasilis Naskos <vnaskos.com>
 */
public class ValidationError {
    
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<String> errors;

    private final String errorMessage;

    public ValidationError(String errorMessage) {
        this.errors = new ArrayList<>();
        this.errorMessage = errorMessage;
    }

    public void addValidationError(String error) {
        errors.add(error);
    }

    public List<String> getErrors() {
        return errors;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
