package com.user.service.utils.convertor;

import com.user.service.dto.user.UserRegistrationDto;
import com.user.service.dto.basket.BasketProductDto;
import com.user.service.dto.basket.BasketResponse;
import com.user.service.dto.wishList.WishListResponse;
import com.user.service.entities.Basket;
import com.user.service.entities.BasketProduct;
import com.user.service.entities.User;
import com.user.service.entities.UserPrivateData;
import com.user.service.entities.WishList;
import lombok.RequiredArgsConstructor;
import ua.cheesecake.dto.UserDto;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Convertor {

    public WishListResponse convert(WishList wishList) {
        return WishListResponse.builder()
                .id(wishList.getId().toString())
                .wishProductList(wishList.getProductIds())
                .build();
    }

    public BasketProduct convert(Basket basket, String productId, Integer count) {
        BasketProduct product = new BasketProduct();
        product.setBasket(basket);
        product.setProductId(productId);
        product.setCount(count);
        return product;
    }

    public BasketResponse convert(Basket basket) {
        return BasketResponse.builder()
                .userId(basket.getId())
                .basketProductList(
                        basket.getProductList()
                                .stream()
                                .map(this::getBasketProductDto)
                                .toList())
                .build();
    }

    public User convert(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        user.setName(userRegistrationDto.getName());
        user.setSecondName(userRegistrationDto.getSecondName());
        return user;
    }
    public UserPrivateData convert(UserRegistrationDto userRegistrationDto, User user) {
        UserPrivateData userPrivateData = new UserPrivateData();
        userPrivateData.setUser(user);
        userPrivateData.setEmail(userRegistrationDto.getEmail());
        userPrivateData.setPassword(userRegistrationDto.getPassword());
        userPrivateData.setCreateTime(LocalDateTime.now());
        return userPrivateData;
    }

    public UserDto convert(User user) {
        return UserDto
                .builder()
                .id(user.getId())
                .name(user.getName())
                .secondName(user.getSecondName())
                .build();
    }

    public User convert(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setSecondName(userDto.getSecondName());
        return user;
    }

    public WishList convert(List<String> wishList, Long id) {
        WishList list = new WishList();
        list.setId(id);
        list.setProductIds(wishList);
        return list;
    }

    public List<UserDto> convert(List<User> userList) {
        return userList
                .stream()
                .map(user -> UserDto
                        .builder()
                        .id(user.getId())
                        .name(user.getName())
                        .secondName(user.getSecondName())
                        .build())
                .toList();
    }

    public WishList emptyWishList(Long userId) {
        WishList list = new WishList();
        list.setId(userId);
        list.setProductIds(Collections.emptyList());
        return list;
    }

    public Basket emptyBasket(Long userId) {
        Basket basket = new Basket();
        basket.setId(userId);
        return basket;
    }

    public BasketProductDto getBasketProductDto(BasketProduct basketProduct) {
        // TODO create me normal name and make one usage
        return BasketProductDto.builder()
                .productId(basketProduct.getProductId())
                .count(basketProduct.getCount())
                .build();
    }

    public BasketProduct setConvert(BasketProduct basketProduct, Integer count) {
        basketProduct.setCount(count);
        return basketProduct;
    }
}
