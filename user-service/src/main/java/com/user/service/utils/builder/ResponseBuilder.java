package com.user.service.utils.builder;

import com.user.service.dto.basket.BasketProductResponse;
import com.user.service.dto.user.UserResponse;
import com.user.service.dto.wish.WishProductResponse;
import com.user.service.entities.BasketProduct;
import com.user.service.entities.User;
import com.user.service.entities.WishProduct;
import org.springframework.stereotype.Component;

@Component("customResponseBuilder")
public class ResponseBuilder {

    public BasketProductResponse convert(BasketProduct basketProduct) {
        return BasketProductResponse.builder()
                .id(basketProduct.getId())
                .productVersionId(basketProduct.getProductVersionId())
                .count(basketProduct.getCount())
                .build();
    }

    public UserResponse convert(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .secondName(user.getSecondName())
                .blocked(user.getBlocked())
                .deleted(user.getDeleted())
                .build();
    }

    public WishProductResponse convert(WishProduct wishProduct) {
        return WishProductResponse.builder()
                .productVersionId(wishProduct.getProductVersionId())
                .wishListId(wishProduct.getWishList().getId())
                .build();
    }


}
