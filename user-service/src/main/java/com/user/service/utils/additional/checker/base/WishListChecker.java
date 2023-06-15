package com.user.service.utils.additional.checker.base;


import com.user.service.dao.WishListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class WishListChecker {

    private final WishListRepository wishListRepository;

    public boolean check(Long userId) {
        if (!wishListRepository.existsById(userId)) {
            log.info("wishList by userId: {} not found", userId);
            return false;
        }
        return true;
    }
}
