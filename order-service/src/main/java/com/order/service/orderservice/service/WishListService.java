package com.order.service.orderservice.service;

import org.springframework.stereotype.Service;

import com.order.service.orderservice.dto.WishListRequest;
import com.order.service.orderservice.utils.additional.SuperChecker;
import com.order.service.orderservice.utils.request.WishListRequestConstructor;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class WishListService {

    private final SuperChecker superChecker;
    private final WishListRequestConstructor wishListRequestConstructor;

    public boolean addWishItem(String versionId, Long userId) {
        log.debug("addWishItem supplied versionId is: {} \n userId is: {}", versionId, userId);
        superChecker.wishList(userId, versionId);
        wishListRequestConstructor.addItemToWishList(userId, versionId);
        return true;
    }

    public boolean addWishItem(WishListRequest wishListRequest) {
        Long userId = Long.valueOf(wishListRequest.getUserId());
        String versionId = wishListRequest.getProductVersionId();
        log.debug("addWishItem supplied: {}", wishListRequest);
        superChecker.wishList(userId, versionId);
        return wishListRequestConstructor.addItemToWishList(userId, versionId);
    }

    public boolean deleteWishItem(WishListRequest wishListRequest) {
        Long userId = Long.valueOf(wishListRequest.getUserId());
        String versionId = wishListRequest.getProductVersionId();
        log.debug("deleteWishItem supplied: {}", wishListRequest);
        superChecker.wishList(userId, versionId);
        return wishListRequestConstructor.deleteItemFromWishList(userId, versionId);
    }

    public boolean checkItemInWishList(WishListRequest wishListRequest) {
        Long userId = Long.valueOf(wishListRequest.getUserId());
        String versionId = wishListRequest.getProductVersionId();
        return superChecker.checkWishProduct(userId, versionId);
    }

    public List<String> getWishList(Long userId) {
        superChecker.checkUser(userId);
        return wishListRequestConstructor.getWishProducts(userId);
    }
}
