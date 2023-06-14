package com.user.service.service;

import com.user.service.dto.user.*;
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

    public UserResponse create(UserRegistrationRequest userRegistrationRequest) {
        return userRequestConstructor.create(userRegistrationRequest);
    }

    public UserPrivateDataResponse getPrivateData(Long userId) {
        checker.check(userId);
        return convertor.convert(userRequestConstructor.getPrivateData(userId));
    }

    public List<UserDto> get() {
        log.debug("getting all users user");
        return userRequestConstructor.get();
    }

    public UserInfoResponse getUserInfo(Long userId) {
        return userRequestConstructor.getUserInfoResponse(userId);
    }

    @Transactional
    public void delete(Long userId) {
        log.debug("delete user by id: {}", userId);
        checker.check(userId);
        userRequestConstructor.delete(userId);
        superBasketChecker.checkDelete(userId);
        superWishListChecker.checkDelete(userId);
    }

    public UserPrivateDataResponse updateUserPrivateData(UserPrivateDataRequest userPrivateDataRequest) {
        return convertor.convert(userRequestConstructor.updatePrivateData(userPrivateDataRequest));
    }

    public UserResponse updateUser(UserRequest userRequest) {
        return convertor.convert(userRequestConstructor.updateUser(userRequest));
    }
}
