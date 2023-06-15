package com.user.service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public final class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private UserPrivateData userPrivateData;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Basket basket;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private WishList wishList;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private UserNotificationData userNotificationData;

    private String name;

    private String secondName;

    private Boolean blocked;

    private Boolean deleted;
}
