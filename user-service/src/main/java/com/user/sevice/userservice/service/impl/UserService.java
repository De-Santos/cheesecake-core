package com.user.sevice.userservice.service.impl;

import com.user.sevice.userservice.dto.UserRegistrationDto;
import com.user.sevice.userservice.entities.User;

public interface UserService {
    User save(UserRegistrationDto userRegistrationDto);

}
