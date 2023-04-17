package com.user.service.utils.additional.checker;


import com.user.service.utils.additional.checker.base.UserChecker;
import com.user.service.utils.additional.checker.base.WishListChecker;
import com.user.service.utils.additional.checker.base.ProductChecker;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class SuperWishListChecker {

    private final WishListChecker wishListChecker;
    private final UserChecker userChecker;
    private final ProductChecker productChecker;

    public void checkWishListExistence(Long userId, String versionId) {
        userChecker.check(userId);
        wishListChecker.checkCreate(userId);
        productChecker.check(versionId);
        log.debug("--passed all tests, supplied userId: {} and versionId: {}", userId, versionId);
    }

    public boolean checkWishProduct(Long userId, String versionId) {
        userChecker.check(userId);
        return productChecker.check(versionId) &&
        wishListChecker.checkContains(userId, versionId);
    }

    public void checkUser(Long userId) {
        userChecker.check(userId);
    }

    public boolean checkEnv(Long userId) {
        return wishListChecker.check(userId);
    }

    public void checkDelete(Long userId) {
        log.debug("SuperWishListChecker.checkDelete get userId: {}", userId);
        wishListChecker.checkDelete(userId);
    }
}