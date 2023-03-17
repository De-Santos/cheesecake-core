package com.user.service.utils.request;


import com.user.service.dao.WishListRepository;
import com.user.service.entities.WishList;
import com.user.service.exceptions.exceptions.WishListNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class WishListRequestConstructor {

    private final WishListRepository wishRepository;

    public boolean addItemToWishList(Long userId, String versionId) {
        WishList wishList = wishRepository.findById(userId).orElseThrow(WishListNotFoundException::new);
        log.debug("addItemToWishList get versionId: {} \n wishRepository response is: {}", versionId, wishList);
        List<String> products = wishList.getProductIds();
        if (!products.contains(versionId)) products.add(versionId);
        else return false;
        wishRepository.save(wishList);
        return true;
    }

    public boolean deleteItemFromWishList(Long userId, String versionId) {
        WishList wishList = wishRepository.findById(userId).orElseThrow(WishListNotFoundException::new);
        log.debug("deleteIteFromWishList get versionId: {} \n wishRepository response is: {}", versionId, wishList);
        List<String> products = wishList.getProductIds();
        if (products.contains(versionId)) products.remove(versionId);
        else return false;
        wishRepository.save(wishList);
        return true;
    }

    public List<String> getWishProducts(Long userId) {
        WishList wishList = wishRepository.findById(userId).orElseThrow(WishListNotFoundException::new);
        return wishList.getProductIds();
    }

    public void deleteWishList(Long userId) {
        WishList wishList = wishRepository.findById(userId).orElseThrow(WishListNotFoundException::new);
        log.debug("deleteWishList delete wishList by id: {}", userId);
        wishRepository.delete(wishList);
    }
}
