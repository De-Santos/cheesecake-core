package com.user.service.utils.additional.validator.pasword;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.log4j.Log4j2;

import java.util.regex.Pattern;

@Log4j2
public class ValidPasswordConstraint implements
        ConstraintValidator<ValidPassword, String> {
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$");

    @Override
    public void initialize(ValidPassword validPassword) {
    }

    /**
     * Must have at least one numeric character;
     * Must have at least one lowercase character;
     * Must have at least one uppercase character;
     * Must have at least one special symbol among @#$%
     * Password length should be between 8 and 20;
     */
    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        log.debug("Validating contactField is: {}", contactField);
        return validatePassword(contactField);
    }

    private boolean validatePassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }
}
