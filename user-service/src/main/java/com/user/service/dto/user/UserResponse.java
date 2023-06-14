package com.user.service.dto.user;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class UserResponse {
    private Long id;
    private String name;
    private String secondName;
}
