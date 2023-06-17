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
        return ResponseEntity.ok(userService.createUser(userRegistrationRequest));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") Long userId) {
        log.info("Get user user by id: {}", userId);
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable("id") Long userId) {
        log.info("Delete user by id: {}", userId);
        return ResponseEntity.ok(userService.delete(userId));
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> restoreUser(@PathVariable("id") Long userId) {
        log.info("Restore user by id: {}", userId);
        return ResponseEntity.ok(userService.restore(userId));
    }

    @Override
    @GetMapping("/data/{id}")
    public ResponseEntity<UserPrivateDataResponse> getUserPrivateData(@PathVariable(name = "id") Long userId) {
        log.info("Get user private data by id: {}", userId);
        return ResponseEntity.ok(userService.getPrivateData(userId));
    }

    @Override
    @GetMapping("/info/{id}")
    public ResponseEntity<UserInfoResponse> getUserInfo(@PathVariable(name = "id") Long userId) {
        log.info("Get user info by user id: {}", userId);
        return ResponseEntity.ok(userService.getUserInfo(userId));
    }

    @Override
    @PatchMapping("/data")
    public ResponseEntity<UserPrivateDataResponse> updateUserPrivateData(@RequestBody UserPrivateDataRequest userPrivateDataDto) {
        log.info("Update user private data by user id: {}", userPrivateDataDto.getUserId());
        return ResponseEntity.ok(userService.updateUserPrivateData(userPrivateDataDto));
    }

    @Override
    @PatchMapping()
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest) {
        log.info("Update user by user id: {}", userRequest.getId());
        return ResponseEntity.ok(userService.updateUser(userRequest));
    }

    @Override
    @GetMapping("/{id}/notification/settings")
    public ResponseEntity<UserNotificationSettingsResponse> getUserNotificationSettings(@PathVariable("id") Long userId) {
        log.info("Get user notification settings by user id: {}", userId);
        return ResponseEntity.ok(userService.getUserNotificationSettings(userId));
    }

    @Override
    @PatchMapping("/notification/settings")
    public ResponseEntity<UserNotificationSettingsResponse> updateUserNotificationSettings(@RequestBody UserNotificationSettingsRequest notificationSettingsRequest) {
        log.info("Update user notification settings by user id: {}", notificationSettingsRequest.getId());
        return ResponseEntity.ok(userService.updateNotificationSettings(notificationSettingsRequest));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        log.info("Get all users");
        return ResponseEntity.ok(userService.get());
    }
}
