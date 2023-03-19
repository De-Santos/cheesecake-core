package com.user.service.utils.request;

import java.util.List;

import com.user.service.entities.User;

import com.user.service.entities.UserPrivateData;
import com.user.service.exceptions.exceptions.UserPrivateDataNotFoundException;
import org.springframework.stereotype.Component;

import com.user.service.dao.UserPrivateDataRepository;
import com.user.service.dao.UserRepository;
import com.user.service.utils.convertor.Convertor;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ua.cheesecake.dto.UserDto;
import ua.cheesecake.dto.UserPrivateDataDto;
import ua.cheesecake.dto.exception.UserNotFoundException;

@Log4j2
@Component
@RequiredArgsConstructor
public class UserRequestConstructor {

    private final Convertor convertor;
    private final UserRepository userRepository;
    private final UserPrivateDataRepository userPrivateDataRepository;

    public UserDto create(UserPrivateDataDto userPrivateDataDto) {
        log.debug("User is creating");
        User user = userRepository.save(convertor.convert(userPrivateDataDto));
        userPrivateDataRepository.save(convertor.mergeConvert(userPrivateDataDto, user));
        return convertor.convert(user);
    }

    public List<UserDto> get() {
        log.debug("Getting all users");
        return convertor.convert(userRepository.findAll());
    }

    public void delete(Long userId) {
        log.debug("Deleting user by id: {}", userId);
        userPrivateDataRepository.deleteByUserId(userId);
        userRepository.deleteByUserId(userId);
    }

    public UserPrivateDataDto getPrivateData(Long userId) {
        log.debug("Getting user private data");
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        UserPrivateData userPrivateData = userPrivateDataRepository
                .findById(userId)
                .orElseThrow(UserPrivateDataNotFoundException::new);
        log.debug(userPrivateData);
        return convertor.mergeConvert(userPrivateData, user);

    }

    public UserPrivateDataDto updatePrivateData(UserPrivateDataDto userPrivateDataDto) {
        Long userId = userPrivateDataDto.getUserId();
        log.debug("Updating user private data by id: {}", userId);
        UserPrivateData  userPrivateData = userPrivateDataRepository
                .findById(userId)
                .orElseThrow(UserPrivateDataNotFoundException::new);
        UserPrivateData newUserPrivateData = userPrivateDataRepository
                .save(convertor.updateCovert(userPrivateData, userPrivateDataDto));
        log.debug("Updated user private data is: {}", newUserPrivateData);
        return convertor.convert(newUserPrivateData);

    }

    public UserDto updateUser(UserPrivateDataDto userPrivateDataDto) {
        Long userId = userPrivateDataDto.getUserId();
        log.debug("Updating user data by id: {}", userId);
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        User newUser = userRepository.save(convertor.mergeConvert(user, userPrivateDataDto));
        return convertor.convert(newUser);
    }
}
