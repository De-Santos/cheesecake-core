package com.user.service.utils.additional.checker;


import com.user.service.dao.WishListRepository;
import com.user.service.utils.convertor.Convertor;
import com.user.service.utils.request.WishListRequestConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

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

    public void checkDelete(Long userId) {
        log.debug("WishListChecker.checkDelete delete wishList by id: {}", userId);
        if (!wishListRepository.existsById(userId)) return;
        wishListRequestConstructor.deleteWishList(userId);
    }
}
