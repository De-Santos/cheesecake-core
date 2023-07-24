package com.user.service.utils.request;

import com.user.service.dao.*;
import com.user.service.dto.user.*;
import com.user.service.entities.User;
import com.user.service.entities.UserPrivateData;
import com.user.service.exceptions.exceptions.user.found.UserNotFoundException;
import com.user.service.exceptions.exceptions.user.found.UserNotificationSettingsNotFoundException;
import com.user.service.exceptions.exceptions.user.found.UserPrivateDataNotFoundException;
import com.user.service.utils.additional.checker.base.UserChecker;
import com.user.service.utils.builder.EntityBuilder;
import com.user.service.utils.convertor.Converter;
import com.user.service.utils.request.jdbc.accelerator.JdbcAccelerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.cheesecake.dto.UserDto;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserRequestConstructor {

    private final Converter converter;
    private final UserRepository userRepository;
    private final UserNotificationSettingsRepository userNotificationSettingsRepository;
    private final UserPrivateDataRepository userPrivateDataRepository;
    private final WishListRepository wishListRepository;
    private final BasketRepository basketRepository;
    private final JdbcAccelerator accelerator;
    private final EntityBuilder entityBuilder;
    private final UserChecker userChecker;

    @Transactional
    public User create(UserRegistrationRequest userRegistrationRequest) {
        User user = userRepository.save(converter.convert(userRegistrationRequest));
        userPrivateDataRepository.save(converter.convert(userRegistrationRequest, user));
        userNotificationSettingsRepository.save(entityBuilder.userNotificationSettingsFrom(user));
        wishListRepository.save(entityBuilder.wishListFrom(user));
        basketRepository.save(entityBuilder.basketFrom(user));
        return user;
    }

    public List<UserDto> get() {
        return converter.convert(userRepository.findAll());
    }

    public UserResponse delete(Long userId) {
        return accelerator.deletedById(userId)
                .orElseThrow(() -> UserNotFoundException.create(userId));
    }

    public UserResponse restore(Long userId) {
        return accelerator.restoreById(userId)
                .orElseThrow(() -> UserNotFoundException.create(userId));
    }

    public UserResponse getUserById(Long userId) {
        return accelerator.getUserResponseById(userId)
                .orElseThrow(() -> UserNotFoundException.create(userId));
    }

    public UserPrivateData getPrivateData(Long userId) {
        return userPrivateDataRepository
                .findById(userId)
                .orElseThrow(() -> UserPrivateDataNotFoundException.create(userId));
    }

    public UserNotificationSettingsResponse getNotificationSettings(Long userId) {
        return accelerator.getUserNotificationSettings(userId)
                .orElseThrow(() -> UserNotificationSettingsNotFoundException.create(userId));
    }

    public UserNotificationSettingsResponse updateNotificationSettings(UserNotificationSettingsRequest notificationSettingsRequest) {
        return accelerator.updateUserNotificationSettings(notificationSettingsRequest)
                .orElseThrow(() -> UserNotificationSettingsNotFoundException.create(notificationSettingsRequest.getId()));
    }

    public UserInfoResponse getUserInfoResponse(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.create(userId));
        return converter.convert(user.getUserPrivateData(), user);
    }

    public UserPrivateData updatePrivateData(UserPrivateDataRequest userPrivateDataDto) {
        userChecker.check(userPrivateDataDto.getUserId());
        return userPrivateDataRepository.save(
                converter.updateConvert(
                        userPrivateDataDto,
                        userPrivateDataRepository.getDateById(userPrivateDataDto.getUserId())
                )
        );
    }

    public UserResponse updateUser(UserRequest userRequest) {
        return accelerator.updateUserById(userRequest)
                .orElseThrow(() -> UserNotFoundException.create(userRequest.getId()));
    }
}
