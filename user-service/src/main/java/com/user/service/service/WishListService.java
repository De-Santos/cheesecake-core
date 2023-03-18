package com.user.service.service;


import com.user.service.dto.wishList.WishListRequest;
import com.user.service.utils.request.WishListRequestConstructor;
import com.user.service.utils.additional.checker.SuperWishListChecker;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class WishListService {

    private final SuperWishListChecker superWishListChecker;
    private final WishListRequestConstructor wishListRequestConstructor;

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
