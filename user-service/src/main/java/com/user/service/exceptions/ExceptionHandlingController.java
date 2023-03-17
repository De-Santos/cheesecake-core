package com.user.service.exceptions;


import com.user.service.exceptions.exceptions.BasketProductNotFoundException;
import com.user.service.exceptions.exceptions.WishListNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.cheesecake.dto.exception.ProductNotFoundException;
import ua.cheesecake.dto.exception.UserNotFoundException;

import java.time.LocalDateTime;

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

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleException(ProductNotFoundException e) {
        return new ResponseEntity<>(
            new ExceptionDto(e.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BasketProductNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleException(BasketProductNotFoundException e) {
        return new ResponseEntity<>(
            new ExceptionDto(e.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }
}
