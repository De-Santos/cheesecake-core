package com.user.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationDto {
    private String name ;
    private String secondName;
}
