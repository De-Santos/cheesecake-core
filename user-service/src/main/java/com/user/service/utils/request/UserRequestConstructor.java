package com.user.service.utils.request;

import java.util.List;

import com.user.service.dto.UserRegistrationDto;
import org.springframework.stereotype.Component;

import com.user.service.dao.UserRepository;
import com.user.service.utils.additional.SuperBasketChecker;
import com.user.service.utils.additional.SuperWishListChecker;
import com.user.service.utils.convertor.Convertor;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ua.cheesecake.dto.UserDto;

@Log4j2
@Component
@RequiredArgsConstructor
public class UserRequestConstructor {

    private final Convertor convertor;
    private final UserRepository userRepository;
    private final SuperBasketChecker superBasketChecker;
    private final SuperWishListChecker superWishListChecker;

    public UserDto create(UserRegistrationDto userRegistrationDto) {
        log.debug("User is creating");
        return convertor.convert(userRepository.save(convertor.convert(userRegistrationDto)));
    }

    public List<UserDto> get() {
        log.debug("Getting all users");
        return convertor.convert(userRepository.findAll());
    }

    public void delete(Long userId) {
        log.debug("Deleting user by id: {}", userId);
        userRepository.deleteById(userId);
        superBasketChecker.checkDelete(userId);
        superWishListChecker.checkDelete(userId);
        log.debug("Delete successful user by id: {} ", userId);
    }
}
