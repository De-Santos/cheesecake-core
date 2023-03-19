package com.user.service.controller.user;

import com.user.service.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import ua.cheesecake.dto.UserDto;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import ua.cheesecake.dto.UserPrivateDataDto;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController implements UserApi {
    private final UserService userService;

    @Override
    @PostMapping("/registration")
    public ResponseEntity<UserDto> registration(@RequestBody UserPrivateDataDto userPrivateDataDto) {
        log.info("Registration user");
        log.debug(userPrivateDataDto);
        return ResponseEntity.ok(userService.create(userPrivateDataDto));
    }

    @Override
    @PostMapping("/{id}")
    public ResponseEntity<UserPrivateDataDto> getUserPrivateData(@PathVariable(name = "id") Long userId) {
        log.info("Get user private data");
        log.debug(userId);
        return ResponseEntity.ok(userService.getPrivateData(userId));
    }

    @Override
    @PatchMapping()
    public ResponseEntity<UserPrivateDataDto> updateUserPrivateData(@RequestBody UserPrivateDataDto userPrivateDataDto) {
        log.info("Update user private data");
        log.debug(userPrivateDataDto);
        return ResponseEntity.ok(userService.updateData(userPrivateDataDto));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        log.info("Get all users");
        return ResponseEntity.ok(userService.get());
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(name = "id") Long userId) {
        log.info("Delete user by id");
        log.debug(userId);
        userService.delete(userId);
    }
}
