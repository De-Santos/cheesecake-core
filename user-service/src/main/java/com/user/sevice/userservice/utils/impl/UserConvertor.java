package com.user.sevice.userservice.utils.impl;

import com.user.sevice.userservice.dto.UserRegistrationDto;
import com.user.sevice.userservice.entities.User;
import org.springframework.stereotype.Component;
import ua.cheesecake.dto.UserDto;

@Component
public class UserConvertor {
    public User convertor(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        user.setName(userRegistrationDto.getName());
        user.setSecondName(userRegistrationDto.getSecondName());
//        UserPrivateData userPrivateData = new UserPrivateData();
//        userPrivateData.setEmail(userRegistrationDto.getEmail());
//        userPrivateData.setPassword(userRegistrationDto.getPassword());
//        userPrivateData.setCreateTime(LocalDateTime.now());
//        user.setUserPrivateData(userPrivateData);
        return user;
    }

    public User convertor(UserDto userDto) {
        return null;
    }
}
