package com.order.service.orderservice.utils.convertor;

import com.order.service.orderservice.dto.basket.BasketProductDto;
import com.order.service.orderservice.dto.basket.BasketResponse;
import com.order.service.orderservice.dto.wishList.WishListResponse;
import com.order.service.orderservice.entities.Basket;
import com.order.service.orderservice.entities.BasketProduct;
import com.order.service.orderservice.entities.WishList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
