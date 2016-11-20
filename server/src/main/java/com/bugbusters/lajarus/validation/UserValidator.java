package com.bugbusters.lajarus.validation;

import com.bugbusters.lajarus.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Vasilis Naskos <vnaskos.com>
 */
@Component
public class UserValidator implements Validator {

    @Autowired
    private UserDetailsService userDetailService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username",
                "NotEmpty", "Username field is empty");
        if (user.getUsername().length() < 4 || user.getUsername().length() > 50) {
            errors.rejectValue("username", "Size.userForm.username",
                    "Username must be 4-50 characters long");
        }
        if (userDetailService.loadUserByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username",
                    "Username already exists");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", 
                "NotEmpty", "Password field is empty");
        if (user.getPassword().length() < 4 || user.getPassword().length() > 100) {
            errors.rejectValue("password", "Size.userForm.password",
                    "Password size must be 4-50 characters long");
        }
        
        /*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", 
                "NotEmpty", "Firstname field is emtpy");
        if (user.getFirstname().length() < 4 || user.getFirstname().length() > 50) {
            errors.rejectValue("firstname", "Size.userForm.firstname",
                    "Firstname must be 4-50 characters long");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "NotEmpty"
                , "Lastname field is empty");
        if (user.getLastname().length() < 4 || user.getLastname().length() > 50) {
            errors.rejectValue("lastname", "Size.userForm.lastname",
                    "Lastname must be 4-50 characters long");
        }*/
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
                "NotEmpty", "Email field is empty");
        if (user.getEmail().length() < 4 || user.getEmail().length() > 50) {
            errors.rejectValue("email", "Size.userForm.email");
        }
    }
}
