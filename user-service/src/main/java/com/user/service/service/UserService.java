package com.user.service.service;

import com.user.service.dto.user.*;
import com.user.service.utils.additional.checker.base.UserChecker;
import com.user.service.utils.builder.ResponseBuilder;
import com.user.service.utils.convertor.Converter;
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
    private final Converter converter;
    private final UserRequestConstructor userRequestConstructor;
    private final UserChecker checker;
    private final ResponseBuilder responseBuilder;

    public UserResponse create(UserRegistrationRequest userRegistrationRequest) {
        return responseBuilder.convert(userRequestConstructor.create(userRegistrationRequest));
    }

    public UserPrivateDataResponse getPrivateData(Long userId) {
        checker.check(userId);
        return converter.convert(userRequestConstructor.getPrivateData(userId));
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
    }

    public UserPrivateDataResponse updateUserPrivateData(UserPrivateDataRequest userPrivateDataRequest) {
        return converter.convert(userRequestConstructor.updatePrivateData(userPrivateDataRequest));
    }

    public UserResponse updateUser(UserRequest userRequest) {
        return converter.convert(userRequestConstructor.updateUser(userRequest));
    }
}
