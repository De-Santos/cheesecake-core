package com.user.service.utils.convertor;

import com.user.service.dto.basket.BasketProductDto;
import com.user.service.dto.basket.BasketResponse;
import com.user.service.dto.wishList.WishListResponse;
import com.user.service.entities.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.cheesecake.dto.TimeMapper;
import ua.cheesecake.dto.UserDto;
import ua.cheesecake.dto.UserPrivateDataDto;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Convertor {

    private final TimeMapper timeMapper = new TimeMapper();

    public WishListResponse convert(WishList wishList) {
        return WishListResponse.builder()
                .id(wishList.getId().toString())
                .wishProductList(wishList.getProductIds())
                .build();
    }

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

    public User convert(UserPrivateDataDto userPrivateDataDto) {
        User user = new User();
        user.setName(userPrivateDataDto.getName());
        user.setSecondName(userPrivateDataDto.getSecondName());
        return user;
    }

    public UserPrivateData mergeConvert(UserPrivateDataDto userPrivateDataDto, User user) {
        UserPrivateData userPrivateData = new UserPrivateData();
        userPrivateData.setUser(user);
        userPrivateData.setEmail(userPrivateDataDto.getEmail());
        userPrivateData.setPassword(userPrivateDataDto.getPassword());
        userPrivateData.setPhoneNumber(userPrivateDataDto.getPhoneNumber());
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

    public UserPrivateDataDto mergeConvert(UserPrivateData userPrivateData, User user) {
        return UserPrivateDataDto.builder()
                .userId(user.getId())
                .name(user.getName())
                .secondName(user.getSecondName())
                .email(userPrivateData.getEmail())
                .password(userPrivateData.getPassword())
                .address(userPrivateData.getAddress())
                .phoneNumber(userPrivateData.getPhoneNumber())
                .createTime(timeMapper.toTime(userPrivateData.getCreateTime()))
                .build();
    }

    public UserPrivateDataDto convert(UserPrivateData userPrivateData) {
        return UserPrivateDataDto.builder()
                .email(userPrivateData.getEmail())
                .password(userPrivateData.getPassword())
                .address(userPrivateData.getAddress())
                .phoneNumber(userPrivateData.getPhoneNumber())
                .createTime(timeMapper.toTime(userPrivateData.getCreateTime()))
                .build();
    }

    public UserPrivateDataDto mergeConvert(UserDto user, UserPrivateDataDto userPrivateDataDto) {
        return UserPrivateDataDto.builder()
                .name(user.getName())
                .secondName(user.getSecondName())
                .email(userPrivateDataDto.getEmail())
                .password(userPrivateDataDto.getPassword())
                .address(userPrivateDataDto.getAddress())
                .phoneNumber(userPrivateDataDto.getPhoneNumber())
                .createTime(userPrivateDataDto.getCreateTime())
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

    public UserPrivateData updateCovert(UserPrivateData userPrivateData, UserPrivateDataDto userPrivateDataDto) {
        userPrivateData.setEmail(userPrivateDataDto.getEmail());
        userPrivateData.setPassword(userPrivateDataDto.getPassword());
        userPrivateData.setAddress(userPrivateDataDto.getAddress());
        userPrivateData.setPhoneNumber(userPrivateDataDto.getPhoneNumber());
        return userPrivateData;
    }

    public User mergeConvert(User user, UserPrivateDataDto userPrivateDataDto) {
        user.setName(userPrivateDataDto.getName());
        user.setSecondName(userPrivateDataDto.getSecondName());
        return user;
    }
}
