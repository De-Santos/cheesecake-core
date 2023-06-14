package com.user.service.utils.additional.checker;


import com.user.service.utils.additional.checker.base.BasketChecker;
import com.user.service.utils.additional.checker.base.ProductChecker;
import com.user.service.utils.additional.checker.base.UserChecker;
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

    public void checkBasketExistence(Long userId, String versionId) {
        userChecker.check(userId);
        basketChecker.checkCreate(userId);
        productChecker.check(versionId);
    }

    public void check(Long userId) {
        userChecker.check(userId);
        basketChecker.check(userId);
        log.debug("--passed all tests");
    }

    public void checkDelete(Long userId) {
        log.debug("SuperBasketChecker.checkDelete get userId: {}", userId);
        basketChecker.checkDelete(userId);
    }
}
