package com.user.sevice.userservice.controller;

import com.user.sevice.userservice.dto.UserRegistrationDto;
import com.user.sevice.userservice.entities.User;
import com.user.sevice.userservice.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<User> registration(@RequestBody UserRegistrationDto userRegistrationDto) {
        log.info("add new user (show user Id in debug profile)");
        return ResponseEntity.ok(userService.save(userRegistrationDto));
    }
}
