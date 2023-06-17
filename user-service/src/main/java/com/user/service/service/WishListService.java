package com.user.service.service;


import com.user.service.dto.wish.WishListRequest;
import com.user.service.dto.wish.WishProductResponse;
import com.user.service.utils.request.WishListRequestConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class WishListService {

    private final WishListRequestConstructor wishListRequestConstructor;

    public WishProductResponse addWishItem(WishListRequest wishListRequest) {
        return wishListRequestConstructor.addItemToWishList(wishListRequest);
    }

    public boolean deleteWishItem(WishListRequest wishListRequest) {
        return wishListRequestConstructor.deleteItemFromWishList(wishListRequest);
    }

    public boolean checkItemInWishList(WishListRequest wishListRequest) {
        return wishListRequestConstructor.checkWishProduct(wishListRequest);
    }

    public List<Long> getWishList(Long userId) {
        return wishListRequestConstructor.getWishProducts(userId);
    }
}
