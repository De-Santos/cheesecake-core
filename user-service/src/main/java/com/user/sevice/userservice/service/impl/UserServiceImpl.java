package com.user.sevice.userservice.service.impl;

import com.user.sevice.userservice.dao.UserRepository;
import com.user.sevice.userservice.dto.UserRegistrationDto;
import com.user.sevice.userservice.service.UserService;
import com.user.sevice.userservice.service.utils.UserConvertor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserConvertor userConvertor;
    @Override
    public void save(UserRegistrationDto userRegistrationDto) {
        userRepository.save(userConvertor.convertor(userRegistrationDto));
    }
}
