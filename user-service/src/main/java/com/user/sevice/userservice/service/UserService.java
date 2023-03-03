package com.user.sevice.userservice.service;

import com.user.sevice.userservice.dto.UserRegistrationDto;
import com.user.sevice.userservice.entities.User;

public interface UserService {
    User save(UserRegistrationDto userRegistrationDto);

}
