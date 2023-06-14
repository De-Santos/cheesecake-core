package com.user.service.utils.request;

import com.user.service.dao.UserPrivateDataRepository;
import com.user.service.dao.UserRepository;
import com.user.service.dto.user.*;
import com.user.service.entities.User;
import com.user.service.entities.UserPrivateData;
import com.user.service.exceptions.exceptions.UserPrivateDataNotFoundException;
import com.user.service.utils.additional.checker.base.UserChecker;
import com.user.service.utils.convertor.Convertor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.cheesecake.dto.UserDto;
import ua.cheesecake.dto.exception.UserNotFoundException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserRequestConstructor {

    private final Convertor convertor;
    private final UserRepository userRepository;
    private final UserPrivateDataRepository userPrivateDataRepository;
    private final UserChecker userChecker;

    public UserResponse create(UserRegistrationRequest userRegistrationRequest) {
        User user = userRepository.save(convertor.convert(userRegistrationRequest));
        userPrivateDataRepository.save(convertor.mergeConvert(userRegistrationRequest, user));
        return convertor.convert(user);
    }

    public List<UserDto> get() {
        return convertor.convert(userRepository.findAll());
    }

    public void delete(Long userId) {
        userPrivateDataRepository.deleteByUserId(userId);
        userRepository.deleteByUserId(userId);
    }

    public UserPrivateData getPrivateData(Long userId) {
        return userPrivateDataRepository
                .findById(userId)
                .orElseThrow(UserPrivateDataNotFoundException::new);
    }

    public UserInfoResponse getUserInfoResponse(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found by id" + userId));
        return convertor.mergeConvert(user.getUserPrivateData(), user);
    }

    public UserPrivateData updatePrivateData(UserPrivateDataRequest userPrivateDataDto) {
        userChecker.check(userPrivateDataDto.getUserId());
        return userPrivateDataRepository.save(
                convertor.updateConvert(
                        userPrivateDataDto,
                        userPrivateDataRepository.getDateById(userPrivateDataDto.getUserId())
                )
        );
    }

    public User updateUser(UserRequest userRequest) {
        userChecker.check(userRequest.getId());
        return userRepository.save(convertor.convert(userRequest));
    }
}
