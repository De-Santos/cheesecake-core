package com.user.sevice.userservice.service.utils;


import com.user.sevice.userservice.dto.UserRegistrationDto;
import com.user.sevice.userservice.entities.User;
import org.springframework.stereotype.Component;
import ua.cheesecake.dto.UserDto;

@Component
public interface UserConvertor {
    User convertor(UserRegistrationDto userRegistrationDto);

    User convertor(UserDto userDto);
}
