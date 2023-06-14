package com.user.service.utils.convertor;

import com.user.service.dto.basket.BasketProductDto;
import com.user.service.dto.basket.BasketResponse;
import com.user.service.dto.user.*;
import com.user.service.entities.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.cheesecake.dto.UserDto;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Convertor {

    public BasketProduct mergeConvert(Basket basket, String productId, Integer count) {
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

    public UserPrivateData mergeConvert(UserRegistrationRequest userRegistrationRequest, User user) {
        return UserPrivateData.builder()
                .user(user)
                .email(userRegistrationRequest.getEmail())
                .password(userRegistrationRequest.getPassword())
                .address(userRegistrationRequest.getAddress())
                .phoneNumber(userRegistrationRequest.getPhoneNumber())
                .creationTime(new Date())
                .build();
    }

    public UserPrivateData updateConvert(UserPrivateDataRequest userPrivateDataRequest, Date date) {
        return UserPrivateData.builder()
                .id(userPrivateDataRequest.getUserId())
                .email(userPrivateDataRequest.getEmail())
                .address(userPrivateDataRequest.getAddress())
                .phoneNumber(userPrivateDataRequest.getPhoneNumber())
                .creationTime(date)
                .build();
    }

    public UserResponse convert(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .secondName(user.getSecondName())
                .build();
    }

    public User convert(UserRequest userDto) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .secondName(userDto.getSecondName())
                .build();
    }

    public User convert(UserRegistrationRequest userRegistrationRequest) {
        return User.builder()
                .name(userRegistrationRequest.getName())
                .secondName(userRegistrationRequest.getSecondName())
                .build();
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

    public UserInfoResponse mergeConvert(UserPrivateData userPrivateData, User user) {
        return UserInfoResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .secondName(user.getSecondName())
                .email(userPrivateData.getEmail())
                .address(userPrivateData.getAddress())
                .phoneNumber(userPrivateData.getPhoneNumber())
                .createTime(userPrivateData.getCreationTime())
                .build();
    }

    public UserPrivateDataResponse convert(UserPrivateData userPrivateData) {
        return UserPrivateDataResponse.builder()
                .userId(userPrivateData.getId())
                .email(userPrivateData.getEmail())
                .address(userPrivateData.getAddress())
                .phoneNumber(userPrivateData.getPhoneNumber())
                .createTime(userPrivateData.getCreationTime())
                .build();
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
