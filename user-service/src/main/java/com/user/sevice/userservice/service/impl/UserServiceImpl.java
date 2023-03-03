package com.user.sevice.userservice.service.impl;

import com.user.sevice.userservice.dao.UserRepository;
import com.user.sevice.userservice.dto.UserRegistrationDto;
import com.user.sevice.userservice.entities.User;
import com.user.sevice.userservice.service.UserService;
import com.user.sevice.userservice.service.utils.UserConvertor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserConvertor userConvertor;

    @Override
    public User save(UserRegistrationDto userRegistrationDto) {
        log.debug("supplied user dto is: {}", userRegistrationDto);
        User user = userRepository.save(userConvertor.convertor(userRegistrationDto));
        log.debug("user id: {}", user.getId());
        return user;
    }
}
