package com.user.sevice.userservice.entities;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;

@Entity
@Data
public class UserPrivateData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String address;

    private Integer phoneNumber;

    private LocalDateTime createTime;

    @OneToOne(mappedBy = "userPrivateData")
    private User user;

}
