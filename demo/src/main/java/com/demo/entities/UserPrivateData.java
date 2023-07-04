package com.demo.entities;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserPrivateData {
    private Long id;
    private Long userId;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
    private Date creationTime;
}
