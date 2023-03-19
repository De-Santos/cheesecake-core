package ua.cheesecake.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class UserPrivateDataDto {
    private Long userId;
    private String name;
    private String secondName;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
    private String createTime;
}
