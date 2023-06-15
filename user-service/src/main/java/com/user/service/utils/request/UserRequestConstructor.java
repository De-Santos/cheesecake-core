package com.user.service.utils.request;

import com.user.service.dao.BasketRepository;
import com.user.service.dao.UserPrivateDataRepository;
import com.user.service.dao.UserRepository;
import com.user.service.dao.WishListRepository;
import com.user.service.dto.user.UserInfoResponse;
import com.user.service.dto.user.UserPrivateDataRequest;
import com.user.service.dto.user.UserRegistrationRequest;
import com.user.service.dto.user.UserRequest;
import com.user.service.entities.User;
import com.user.service.entities.UserPrivateData;
import com.user.service.exceptions.exceptions.UserPrivateDataNotFoundException;
import com.user.service.utils.additional.checker.base.UserChecker;
import com.user.service.utils.builder.EntityBuilder;
import com.user.service.utils.convertor.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.cheesecake.dto.UserDto;
import ua.cheesecake.dto.exception.UserNotFoundException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserRequestConstructor {

    private final Converter converter;
    private final UserRepository userRepository;
    private final UserPrivateDataRepository userPrivateDataRepository;
    private final WishListRepository wishListRepository;
    private final BasketRepository basketRepository;
    private final EntityBuilder entityBuilder;
    private final UserChecker userChecker;

    @Transactional
    public User create(UserRegistrationRequest userRegistrationRequest) {
        User user = userRepository.save(converter.convert(userRegistrationRequest));
        userPrivateDataRepository.save(converter.convert(userRegistrationRequest, user));
        wishListRepository.save(entityBuilder.wishListFrom(user));
        basketRepository.save(entityBuilder.basketFrom(user));
        return user;
    }

    public List<UserDto> get() {
        return converter.convert(userRepository.findAll());
    }

    public void delete(Long userId) {
        userRepository.markDeletedById(userId);
    }

    public UserPrivateData getPrivateData(Long userId) {
        return userPrivateDataRepository
                .findById(userId)
                .orElseThrow(UserPrivateDataNotFoundException::new);
    }

    public UserInfoResponse getUserInfoResponse(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found by id" + userId));
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

    public User updateUser(UserRequest userRequest) {
        userChecker.check(userRequest.getId());
        return userRepository.save(converter.convert(userRequest));
    }
}
