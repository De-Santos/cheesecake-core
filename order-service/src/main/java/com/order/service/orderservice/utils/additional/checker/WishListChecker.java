package com.order.service.orderservice.utils.additional.checker;

import org.springframework.stereotype.Component;

import com.order.service.orderservice.dao.WishListRepository;
import com.order.service.orderservice.utils.convertor.Convertor;
import com.order.service.orderservice.utils.request.WishListRequestConstructor;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@RequiredArgsConstructor
public class WishListChecker {

    private final WishListRepository wishListRepository;
    private final WishListRequestConstructor wishListRequestConstructor;
    private final Convertor convertor;

    public void checkCreate(Long userId) {
        if (!wishListRepository.existsById(userId)) {
            wishListRepository.save(convertor.emptyWishList(userId));
            log.info("Force create wishList for user: {} ; Because wishList for this user doesn't found", userId);
        }
        log.info("wishList for user: {} was found", userId);
    }

    public boolean check(Long userId) {
        if (!wishListRepository.existsById(userId)) {
            log.info("wishList by userId: {} not found", userId);
            return false;
        }
        return true;
    }

    public boolean checkContains(Long userId, String productId) {
        if (wishListRequestConstructor.getWishProducts(userId).contains(productId)) {
            log.debug("wishList by userId: {} and productId: {} was found", userId, productId);
            return true;
        }
        log.debug("wishList by userId: {} and productId: {} was not found", userId, productId);
        return false;
    }
}
