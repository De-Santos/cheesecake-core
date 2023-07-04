package com.demo.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class User {
    private Long id;
    private UserPrivateData userPrivateData;
    private Basket basket;
    private WishList wishList;
    private Long userNotificationSettingsId;
    private String name;
    private String secondName;
    private Boolean blocked;
    private Boolean deleted;
}
