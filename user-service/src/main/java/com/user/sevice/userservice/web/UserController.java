package com.user.sevice.userservice.web;

import com.user.sevice.userservice.dto.UserRegistrationDto;
import com.user.sevice.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/registration")
    public UserRegistrationDto registration(@RequestBody UserRegistrationDto userRegistrationDto){
        userService.save(userRegistrationDto);
        return userRegistrationDto;
    }
}
