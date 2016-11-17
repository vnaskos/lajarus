package com.bugbusters.lajarus.validation;

import org.springframework.validation.Errors;

/**
 *
 * @author Vasilis Naskos <vnaskos.com>
 */
public class ValidationErrorBuilder {
    
    public static ValidationError fromBindingErrors(Errors errors) {
        ValidationError error = new ValidationError(
                "Validation failed. " + errors.getErrorCount() + " error(s)");
        
        errors.getAllErrors().forEach((objectError) -> {
            error.addValidationError(objectError.getDefaultMessage());
        });
        
        return error;
    }
    
}
