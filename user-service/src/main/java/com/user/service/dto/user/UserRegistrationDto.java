package com.user.service.dto.user;

import lombok.extern.jackson.Jacksonized;
import org.springframework.validation.annotation.Validated;

import com.user.service.utils.additional.validator.email.ValidEmail;
import com.user.service.utils.additional.validator.pasword.ValidPassword;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Jacksonized
public class UserRegistrationDto {
    private String name;
    private String secondName;
    @ValidEmail
    private String email;
    @ValidPassword
    private String password;
}
