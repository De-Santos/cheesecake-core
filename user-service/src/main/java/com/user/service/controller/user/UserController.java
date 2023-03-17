package com.user.service.controller.user;

import com.user.service.dto.UserRegistrationDto;
import com.user.service.service.UserService;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import ua.cheesecake.dto.UserDto;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController implements UserApi {
    private final UserService userService;

    @Override
    @PostMapping("/registration")
    public ResponseEntity<UserDto> registration(@RequestBody UserRegistrationDto userRegistrationDto) {
        log.info("Registration user");
        log.debug(userRegistrationDto);
        return ResponseEntity.ok(userService.create(userRegistrationDto));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        log.info("Get all users");
        return ResponseEntity.ok(userService.get());
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteUser(@NotNull @PathVariable(name = "id") Long userId) {
        log.info("Delete user by id");
        log.debug(userId);
        userService.delete(userId);
    }
}
