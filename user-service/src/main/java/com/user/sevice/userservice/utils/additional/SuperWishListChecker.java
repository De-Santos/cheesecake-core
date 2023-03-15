package com.user.sevice.userservice.utils.additional;


import com.user.sevice.userservice.utils.additional.checker.BasketChecker;
import com.user.sevice.userservice.utils.additional.checker.ProductChecker;
import com.user.sevice.userservice.utils.additional.checker.UserChecker;
import com.user.sevice.userservice.utils.additional.checker.WishListChecker;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class SuperWishListChecker {

    private final WishListChecker wishListChecker;
    private final BasketChecker basketChecker;
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

    public void checkCreateEnv(Long userId) {
        wishListChecker.checkCreate(userId);
        basketChecker.checkCreate(userId);
        log.info("Created env for user: {}", userId);
    }

    public boolean checkEnv(Long userId) {
        return wishListChecker.check(userId);
    }
}
