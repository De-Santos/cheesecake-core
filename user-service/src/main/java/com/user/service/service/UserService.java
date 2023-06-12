package com.user.service.service;

import com.user.service.utils.additional.checker.SuperBasketChecker;
import com.user.service.utils.additional.checker.SuperWishListChecker;
import com.user.service.utils.additional.checker.base.UserChecker;
import com.user.service.utils.convertor.Convertor;
import com.user.service.utils.request.UserRequestConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cheesecake.dto.UserDto;
import ua.cheesecake.dto.UserPrivateDataDto;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserService {
    private final Convertor convertor;
    private final UserRequestConstructor userRequestConstructor;
    private final UserChecker checker;
    private final SuperBasketChecker superBasketChecker;
    private final SuperWishListChecker superWishListChecker;

    public UserDto create(UserPrivateDataDto userPrivateDataDto) {
        log.debug("creating user, userRegistrationDto: {}", userPrivateDataDto);
        return userRequestConstructor.create(userPrivateDataDto);
    }

    public UserPrivateDataDto getPrivateData(Long userId) {
        log.debug("getting user private data by id: {}", userId);
        checker.check(userId);
        return userRequestConstructor.getPrivateData(userId);
    }

    public List<UserDto> get() {
        log.debug("getting all users user");
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

    public UserPrivateDataDto updateData(UserPrivateDataDto userPrivateDataDto) {
        log.debug("update user private data, userPrivateDataDto: {}", userPrivateDataDto);
        return convertor.mergeConvert(
                userRequestConstructor.updateUser(userPrivateDataDto),
                userRequestConstructor.updatePrivateData(userPrivateDataDto));
    }
}
