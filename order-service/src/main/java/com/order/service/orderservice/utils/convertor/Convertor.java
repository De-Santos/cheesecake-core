package com.order.service.orderservice.utils.convertor;

import java.util.Collections;
import java.util.List;

import com.order.service.orderservice.entities.BasketProduct;
import org.springframework.stereotype.Component;

import com.order.service.orderservice.dto.WishListResponse;
import com.order.service.orderservice.entities.Basket;
import com.order.service.orderservice.entities.WishList;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Convertor {

    public WishListResponse convert(WishList wishList) {
        return WishListResponse.builder()
        .id(wishList.getId().toString())
        .wishProductList(wishList.getProductIds())
        .build();
    }

    public WishList convert(List<String> wishList, Long id) {
        WishList list = new WishList();
        list.setId(id);
        list.setProductIds(wishList);
        return list;
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

    public BasketProduct convert() {
        // TODO implement me
        return null;
    }
    
}
