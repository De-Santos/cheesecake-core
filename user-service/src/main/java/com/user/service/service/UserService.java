package com.user.service.service;

import com.user.service.dto.user.UserRegistrationDto;
import com.user.service.utils.additional.checker.SuperBasketChecker;
import com.user.service.utils.additional.checker.SuperWishListChecker;
import com.user.service.utils.additional.checker.base.UserChecker;
import com.user.service.utils.request.UserRequestConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cheesecake.dto.UserDto;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRequestConstructor userRequestConstructor;
    private final UserChecker checker;
    private final SuperBasketChecker superBasketChecker;
    private final SuperWishListChecker superWishListChecker;

    public UserDto create(UserRegistrationDto userRegistrationDto) {
        log.debug("creating user, userRegistrationDto: {}", userRegistrationDto);
        return userRequestConstructor.create(userRegistrationDto);
    }

    public List<UserDto> get() {
        log.debug("get user -UserService");
        return userRequestConstructor.get();
    }

    @Transactional
    public void delete(Long userId) {
        log.debug("delete user by id: {}", userId);
        checker.check(userId);
        userRequestConstructor.delete(userId);
        superBasketChecker.checkDelete(userId);
        superWishListChecker.checkDelete(userId);
    }

}
