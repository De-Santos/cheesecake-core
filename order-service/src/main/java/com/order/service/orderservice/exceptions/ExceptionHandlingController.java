package com.order.service.orderservice.exceptions;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.cheesecake.dto.exception.UserNotFoundException;

@ControllerAdvice
public class ExceptionHandlingController {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleException(UserNotFoundException e) {
        return new ResponseEntity<>(
            new ExceptionDto(e.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WishListNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleException(WishListNotFoundException e) {
        return new ResponseEntity<>(
            new ExceptionDto(e.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }
}
