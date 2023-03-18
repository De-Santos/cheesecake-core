package com.user.service.utils.additional.validator.email;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.validator.routines.EmailValidator;

import com.user.service.exceptions.exceptions.InvalidEmailException;

@Log4j2
public class ValidEmailConstraint implements
        ConstraintValidator<ValidEmail, String> {
    @Override
    public void initialize(ValidEmail validEmail) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        log.debug("Validating contactField is: {}", contactField);
        return validateEmail(contactField);
    }

    private boolean validateEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
