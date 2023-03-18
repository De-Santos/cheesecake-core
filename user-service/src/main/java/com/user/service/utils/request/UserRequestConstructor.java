package com.user.service.utils.request;

import java.util.List;

import com.user.service.dto.user.UserRegistrationDto;
import com.user.service.entities.User;

import org.springframework.stereotype.Component;

import com.user.service.dao.UserPrivateDataRepository;
import com.user.service.dao.UserRepository;
import com.user.service.utils.convertor.Convertor;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ua.cheesecake.dto.UserDto;

@Log4j2
@Component
@RequiredArgsConstructor
public class UserRequestConstructor {

    private final Convertor convertor;
    private final UserRepository userRepository;
    private final UserPrivateDataRepository userPrivateDataRepository;

    public UserDto create(UserRegistrationDto userRegistrationDto) {
        log.debug("User is creating");
        User user = userRepository.save(convertor.convert(userRegistrationDto));
        userPrivateDataRepository.save(convertor.convert(userRegistrationDto, user));
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
}
