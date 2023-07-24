package com.user.service.utils.request;


import com.user.service.dao.WishListRepository;
import com.user.service.dao.WishProductRepository;
import com.user.service.dto.wish.WishListRequest;
import com.user.service.dto.wish.WishProductResponse;
import com.user.service.entities.WishList;
import com.user.service.entities.WishProduct;
import com.user.service.exceptions.exceptions.wish.exist.WishProductAlreadyExistException;
import com.user.service.exceptions.exceptions.wish.found.WishListNotFoundException;
import com.user.service.utils.builder.EntityBuilder;
import com.user.service.utils.builder.ResponseBuilder;
import com.user.service.utils.request.jdbc.accelerator.JdbcAccelerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class WishListRequestConstructor {

    private final WishListRepository wishRepository;
    private final WishProductRepository wishProductRepository;
    private final ResponseBuilder responseBuilder;
    private final JdbcAccelerator accelerator;
    private final EntityBuilder entityBuilder;

    public WishProductResponse addItemToWishList(WishListRequest wishListRequest) {
        if (Boolean.TRUE.equals(accelerator.existWishProductBy(wishListRequest.getUserId(), wishListRequest.getProductVersionId())))
            throw WishProductAlreadyExistException.create(wishListRequest.getUserId(), wishListRequest.getProductVersionId());
        WishList wishList = wishRepository.findById(wishListRequest.getUserId()).orElseThrow(() -> WishListNotFoundException.create(wishListRequest.getUserId()));
        return responseBuilder.convert(wishProductRepository.save(entityBuilder.buildWishProduct(wishList, wishListRequest.getProductVersionId())));
    }

    public boolean deleteItemFromWishList(WishListRequest wishListRequest) {
        if (Boolean.TRUE.equals(accelerator.existWishProductBy(wishListRequest.getUserId(), wishListRequest.getProductVersionId())))
            throw WishProductAlreadyExistException.create(wishListRequest.getUserId(), wishListRequest.getProductVersionId());
        wishProductRepository.deleteByWishListAndProductVersionId(wishListRequest.getProductVersionId(), wishListRequest.getUserId());
        return true;
    }

    public List<Long> getWishProducts(Long userId) {
        return wishRepository.findById(userId)
                .orElseThrow(() -> WishListNotFoundException.create(userId))
                .getProducts()
                .stream()
                .map(WishProduct::getId)
                .toList();
    }

    public boolean checkWishProduct(WishListRequest wishListRequest) {
        return accelerator.existWishProductBy(wishListRequest.getUserId(), wishListRequest.getProductVersionId());
    }
}
