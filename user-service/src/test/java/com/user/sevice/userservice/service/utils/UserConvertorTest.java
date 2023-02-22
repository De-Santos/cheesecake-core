package com.user.sevice.userservice.service.utils;

import com.user.sevice.userservice.dto.UserRegistrationDto;
import com.user.sevice.userservice.entities.User;
import com.user.sevice.userservice.entities.UserPrivateData;
import com.user.sevice.userservice.service.utils.impl.UserConvertorImpl;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserConvertorTest {
    private UserConvertor userConvertor = new UserConvertorImpl();
    @Test
    public void validUserConvertorTest(){
        UserRegistrationDto userRegistrationDto =  UserRegistrationDto.builder()
                .password("Password")
                .email("name@gamil.com")
                .name("Name")
                .secondName("SecondName")
                .build();

        User user = new User();
        user.setName("Name");
        user.setSecondName("SecondName");
        UserPrivateData userPrivateData = new UserPrivateData();
        userPrivateData.setEmail("name@gamil.com");
        userPrivateData.setPassword("Password");
        userPrivateData.setCreateTime(new Time(new Date().getTime()));
        user.setUserPrivateData(userPrivateData);

        assertEquals(userConvertor.convertor(userRegistrationDto), user);
    }

}