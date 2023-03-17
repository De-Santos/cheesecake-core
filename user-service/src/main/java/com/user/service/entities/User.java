package com.user.service.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public final class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String secondName;
}
