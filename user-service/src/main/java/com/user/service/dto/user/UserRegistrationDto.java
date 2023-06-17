package com.user.service.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class UserRegistrationDto {
    @JsonProperty("name")
    private String name;

    @JsonProperty("second_name")
    private String secondName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;
}
