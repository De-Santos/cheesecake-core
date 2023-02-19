package com.user.sevice.userservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationDto {
    private String email;
    private String password;
    private String name ;
    private String secondName;
}
