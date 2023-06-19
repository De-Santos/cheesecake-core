package com.user.service.exceptions.controller;

import com.user.service.exceptions.dto.ExceptionDto;
import com.user.service.exceptions.exceptions.basket.main.BasketException;
import com.user.service.exceptions.exceptions.user.main.UserException;
import com.user.service.exceptions.exceptions.user.main.UserNotificationException;
import com.user.service.exceptions.exceptions.user.main.UserPrivateDataException;
import com.user.service.exceptions.exceptions.wish.main.WishListException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@Log4j2
@ControllerAdvice
public class ExceptionHandlingController {

    private static final String DEFAULT_EXCEPTION_LOG = "Exception: {} with message: {}";

    @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleException(UserException e) {
        log.error(DEFAULT_EXCEPTION_LOG, e.getClass().getName(), e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotificationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleException(UserNotificationException e) {
        log.error(DEFAULT_EXCEPTION_LOG, e.getClass().getName(), e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserPrivateDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleException(UserPrivateDataException e) {
        log.error(DEFAULT_EXCEPTION_LOG, e.getClass().getName(), e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BasketException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleException(BasketException e) {
        log.error(DEFAULT_EXCEPTION_LOG, e.getClass().getName(), e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WishListException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleException(WishListException e) {
        log.error(DEFAULT_EXCEPTION_LOG, e.getClass().getName(), e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleException(RuntimeException e) {
        log.error(DEFAULT_EXCEPTION_LOG, e.getClass().getName(), e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }
}
