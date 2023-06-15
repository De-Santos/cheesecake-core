package com.user.service.utils.additional.checker.base;


import com.user.service.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import ua.cheesecake.dto.exception.UserNotFoundException;

@Log4j2
@Component
@RequiredArgsConstructor
public class UserChecker {

    private final UserRepository userRepository;

    public void check(Long userId) {
        if (!userRepository.existsById(userId)) return;
        throw new UserNotFoundException(userId.toString());
    }
}
