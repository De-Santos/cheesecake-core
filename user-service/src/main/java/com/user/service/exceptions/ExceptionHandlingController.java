package com.user.service.exceptions;

import com.user.service.exceptions.exceptions.BasketProductNotFoundException;
import com.user.service.exceptions.exceptions.WishListNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.cheesecake.dto.exception.ProductNotFoundException;
import ua.cheesecake.dto.exception.UserNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlingController {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionDto> handleException(UserNotFoundException e) {
        return new ResponseEntity<>(
                new ExceptionDto(e.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WishListNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionDto> handleException(WishListNotFoundException e) {
        return new ResponseEntity<>(
                new ExceptionDto(e.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionDto> handleException(ProductNotFoundException e) {
        return new ResponseEntity<>(
                new ExceptionDto(e.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BasketProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionDto> handleException(BasketProductNotFoundException e) {
        return new ResponseEntity<>(
                new ExceptionDto(e.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<List<ExceptionDto>> validationHandle(final MethodArgumentNotValidException ex) {
        List<ExceptionDto> list = ex.getBindingResult().getAllErrors().stream()
                .map(it -> new ExceptionDto(
                                it.getDefaultMessage(),
                                HttpStatus.NOT_ACCEPTABLE,
                                LocalDateTime.now()
                        )
                )
                .toList();
        return new ResponseEntity<>(list, HttpStatus.NOT_ACCEPTABLE);
    }
}
