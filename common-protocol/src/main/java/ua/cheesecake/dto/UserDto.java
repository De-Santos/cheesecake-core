package ua.cheesecake.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class UserDto {
    private Long id;
    private String name;
    private String secondName;
}
