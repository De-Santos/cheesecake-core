package com.user.service.service;

import com.user.service.dto.user.*;
import com.user.service.exceptions.exceptions.user.found.UserNotFoundException;
import com.user.service.exceptions.exceptions.user.found.UserNotificationSettingsNotFoundException;
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

    public UserResponse createUser(UserRegistrationRequest userRegistrationRequest) {
        return responseBuilder.convert(userRequestConstructor.create(userRegistrationRequest));
    }

    public UserPrivateDataResponse getPrivateData(Long userId) {
        checker.check(userId);
        return converter.convert(userRequestConstructor.getPrivateData(userId));
    }

    public UserResponse getUser(Long userId) {
        return userRequestConstructor.getUserById(userId);
    }

    public List<UserDto> get() {
        log.debug("getting all users user");
        return userRequestConstructor.get();
    }

    public UserInfoResponse getUserInfo(Long userId) {
        return userRequestConstructor.getUserInfoResponse(userId);
    }


    /**
     * Marks user as deleted.
     *
     * @param userId By this {@link Long} user will be marked.
     * @return The {@link UserResponse} with update user data.
     * @throws UserNotFoundException Will be thrown is user doesn't found by provided 'userId'.
     */
    @Transactional
    public UserResponse delete(Long userId) {
        return userRequestConstructor.delete(userId);
    }

    /**
     * Marks user as not deleted.
     *
     * @param userId By this {@link Long} user will be marked.
     * @return The {@link UserResponse} with update user data.
     * @throws UserNotFoundException Will be thrown is user doesn't found by provided 'userId'.
     */
    @Transactional
    public UserResponse restore(Long userId) {
        return userRequestConstructor.restore(userId);
    }

    /**
     * Returns user notification settings by 'userId'.
     *
     * @param userId By this {@link Long} user's notification settings will be found.
     * @return The {@link UserNotificationSettingsResponse} wish user notification settings.
     * @throws UserNotificationSettingsNotFoundException Will be thrown
     *                                                   if user notification settings does not exist with provided 'userId'.
     */
    public UserNotificationSettingsResponse getUserNotificationSettings(Long userId) {
        return userRequestConstructor.getNotificationSettings(userId);
    }

    /**
     * Returns user notification settings by 'userId'.
     *
     * @param notificationSettingsRequest By this {@link UserNotificationSettingsRequest} user's notification
     *                                    settings will be updated.
     * @return The {@link UserNotificationSettingsResponse} wish updated user's notification settings.
     * @throws UserNotificationSettingsNotFoundException Will be thrown
     *                                                   if user notification settings does not exist with provided 'UserNotificationSettingsRequest.id'.
     */
    public UserNotificationSettingsResponse updateNotificationSettings(UserNotificationSettingsRequest notificationSettingsRequest) {
        return userRequestConstructor.updateNotificationSettings(notificationSettingsRequest);
    }

    public UserPrivateDataResponse updateUserPrivateData(UserPrivateDataRequest userPrivateDataRequest) {
        return converter.convert(userRequestConstructor.updatePrivateData(userPrivateDataRequest));
    }

    public UserResponse updateUser(UserRequest userRequest) {
        return userRequestConstructor.updateUser(userRequest);
    }
}
