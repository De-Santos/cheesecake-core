package com.user.sevice.userservice.service;


import com.user.sevice.userservice.utils.additional.SuperWishListChecker;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class GlobalService {

    private final SuperWishListChecker superChecker;

    public void createAll(Long userId) {
        log.info("creating wishList and basket for user by id: {}", userId);
        superChecker.checkUser(userId);
        log.debug("user checked successfully by id: {}", userId);
        superChecker.checkCreateEnv(userId);
    }

}
