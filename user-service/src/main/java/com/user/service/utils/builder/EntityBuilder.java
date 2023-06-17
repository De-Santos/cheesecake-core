package com.user.service.utils.builder;

import com.user.service.entities.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EntityBuilder {

    public WishList wishListFrom(User user) {
        return WishList.builder()
                .user(user)
                .build();
    }

    public WishProduct buildWishProduct(WishList wishList, UUID productVersionId) {
        return WishProduct.builder()
                .productVersionId(productVersionId)
                .wishList(wishList)
                .build();
    }

    public Basket basketFrom(User user) {
        return Basket.builder()
                .user(user)
                .build();
    }

    public UserNotificationSettings userNotificationSettingsFrom(User user) {
        return UserNotificationSettings.builder()
                .user(user)
                .emailNotification(true)
                .smsNotification(true)
                .adsNotification(true)
                .build();
    }
}
