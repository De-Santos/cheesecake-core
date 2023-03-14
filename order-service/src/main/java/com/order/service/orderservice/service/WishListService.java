package com.order.service.orderservice.service;

import org.springframework.stereotype.Service;

import com.order.service.orderservice.dto.wishList.WishListRequest;
import com.order.service.orderservice.utils.additional.SuperWishListChecker;
import com.order.service.orderservice.utils.request.WishListRequestConstructor;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class WishListService {

    private final SuperWishListChecker superWishListChecker;
    private final WishListRequestConstructor wishListRequestConstructor;

    public boolean addWishItem(String versionId, Long userId) {
        log.debug("addWishItem supplied versionId is: {} \n userId is: {}", versionId, userId);
        superWishListChecker.checkWishListExistence(userId, versionId);
        wishListRequestConstructor.addItemToWishList(userId, versionId);
        return true;
    }

    public boolean addWishItem(WishListRequest wishListRequest) {
        Long userId = Long.valueOf(wishListRequest.getUserId());
        String versionId = wishListRequest.getProductVersionId();
        log.debug("addWishItem supplied: {}", wishListRequest);
        superWishListChecker.checkWishListExistence(userId, versionId);
        return wishListRequestConstructor.addItemToWishList(userId, versionId);
    }

    public boolean deleteWishItem(WishListRequest wishListRequest) {
        Long userId = Long.valueOf(wishListRequest.getUserId());
        String versionId = wishListRequest.getProductVersionId();
        log.debug("deleteWishItem supplied: {}", wishListRequest);
        superWishListChecker.checkWishListExistence(userId, versionId);
        return wishListRequestConstructor.deleteItemFromWishList(userId, versionId);
    }

    public boolean checkItemInWishList(WishListRequest wishListRequest) {
        Long userId = Long.valueOf(wishListRequest.getUserId());
        String versionId = wishListRequest.getProductVersionId();
        return superWishListChecker.checkWishProduct(userId, versionId);
    }

    public List<String> getWishList(Long userId) {
        superWishListChecker.checkUser(userId);
        return wishListRequestConstructor.getWishProducts(userId);
    }
}
