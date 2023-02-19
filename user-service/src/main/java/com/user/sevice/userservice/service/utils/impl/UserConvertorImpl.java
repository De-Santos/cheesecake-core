package com.user.sevice.userservice.service.utils.impl;

import com.user.sevice.userservice.dto.UserDto;
import com.user.sevice.userservice.dto.UserRegistrationDto;
import com.user.sevice.userservice.entities.User;
import com.user.sevice.userservice.entities.UserPrivateData;
import com.user.sevice.userservice.service.utils.UserConvertor;

import java.sql.Time;
import java.util.Date;

public class UserConvertorImpl implements UserConvertor {
    @Override
    public User convertor(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        user.setName(userRegistrationDto.getName());
        user.setSecondName(userRegistrationDto.getSecondName());
        UserPrivateData userPrivateData = new UserPrivateData();
        userPrivateData.setEmail(userRegistrationDto.getEmail());
        userPrivateData.setPassword(userRegistrationDto.getPassword());
        userPrivateData.setCreateTime(new Time(new Date().getTime()));
        user.setUserPrivateData(userPrivateData);
        return user;
    }

    @Override
    public User convertor(UserDto userDto) {
        return null;
    }
}
