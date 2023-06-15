package com.user.service.utils.builder;

import com.user.service.entities.Basket;
import com.user.service.entities.User;
import com.user.service.entities.WishList;
import com.user.service.entities.WishProduct;
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
}
