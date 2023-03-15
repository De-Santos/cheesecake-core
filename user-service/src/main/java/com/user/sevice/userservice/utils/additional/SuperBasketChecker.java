package com.user.sevice.userservice.utils.additional;


import com.user.sevice.userservice.utils.additional.checker.BasketChecker;
import com.user.sevice.userservice.utils.additional.checker.ProductChecker;
import com.user.sevice.userservice.utils.additional.checker.UserChecker;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class SuperBasketChecker {

    private final UserChecker userChecker;
    private final ProductChecker productChecker;
    private final BasketChecker basketChecker;

    public void checkBasketExistence(Long userId, String versionId, Integer count) {
        userChecker.check(userId);
        basketChecker.checkCreate(userId);
        productChecker.check(versionId);
        log.debug("--passed all tests");
    }

    public void check(Long userId) {
        userChecker.check(userId);
        basketChecker.check(userId);
        log.debug("--passed all tests");
    }

}
