package com.user.service.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_private_data")
public class UserPrivateData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private String email;

    private String password;

    private String address;

    private String phoneNumber;

    private LocalDateTime createTime;

}
