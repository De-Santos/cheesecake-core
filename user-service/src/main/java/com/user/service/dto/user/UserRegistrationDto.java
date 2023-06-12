package com.user.service.dto.user;

import com.user.service.utils.additional.validator.email.ValidEmail;
import com.user.service.utils.additional.validator.pasword.ValidPassword;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

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
