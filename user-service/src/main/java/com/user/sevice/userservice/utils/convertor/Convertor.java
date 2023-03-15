package com.user.sevice.userservice.utils.convertor;


import com.user.sevice.userservice.dto.basket.BasketProductDto;
import com.user.sevice.userservice.dto.basket.BasketResponse;
import com.user.sevice.userservice.dto.wishList.WishListResponse;
import com.user.sevice.userservice.entities.Basket;
import com.user.sevice.userservice.entities.BasketProduct;
import com.user.sevice.userservice.entities.WishList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
                                .toList()
                )
                .build();
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
