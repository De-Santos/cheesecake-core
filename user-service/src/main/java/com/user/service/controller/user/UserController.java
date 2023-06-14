package com.user.service.controller.user;

import com.user.service.dto.user.*;
import com.user.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.cheesecake.dto.UserDto;

import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController implements UserApi {
    private final UserService userService;

    @Override
    @PostMapping("/registration")
    public ResponseEntity<UserResponse> registration(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        log.info("Registration user");
        return ResponseEntity.ok(userService.create(userRegistrationRequest));
    }

    @Override
    @GetMapping("/data/{id}")
    public ResponseEntity<UserPrivateDataResponse> getUserPrivateData(@PathVariable(name = "id") Long userId) {
        log.info("Get user private data");
        return ResponseEntity.ok(userService.getPrivateData(userId));
    }

    @Override
    @GetMapping("/info/{id}")
    public ResponseEntity<UserInfoResponse> getUserInfo(@PathVariable(name = "id") Long userId) {
        log.info("Get user info");
        return ResponseEntity.ok(userService.getUserInfo(userId));
    }

    @Override
    @PatchMapping("/data")
    public ResponseEntity<UserPrivateDataResponse> updateUserPrivateData(@RequestBody UserPrivateDataRequest userPrivateDataDto) {
        log.info("Update user private data");
        return ResponseEntity.ok(userService.updateUserPrivateData(userPrivateDataDto));
    }

    @Override
    @PatchMapping()
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest) {
        log.info("Update user");
        return ResponseEntity.ok(userService.updateUser(userRequest));
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
        userService.delete(userId);
    }
}
