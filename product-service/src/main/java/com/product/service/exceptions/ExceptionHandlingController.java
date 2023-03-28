package com.product.service.exceptions;

import com.product.service.exceptions.request.ProductRequestException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.cheesecake.dto.exception.PhotoNotFoundException;
import ua.cheesecake.dto.exception.ProductNotFoundException;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Log4j2
@ControllerAdvice
public class ExceptionHandlingController {
    private static final String DEFAULT_EXCEPTION_MESSAGE = "Exception {}, exception message: {}";

    @ExceptionHandler(PhotoNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handlePhotoNotFoundException(PhotoNotFoundException e) {
        log.error(DEFAULT_EXCEPTION_MESSAGE, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getName(), e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException e) {
        log.error(DEFAULT_EXCEPTION_MESSAGE, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getName(), e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductRequestException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ExceptionDto> handleProductRequestException(final ProductRequestException e) {
        log.error(DEFAULT_EXCEPTION_MESSAGE, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getName(), e.getMessage(), HttpStatus.NOT_ACCEPTABLE, LocalDateTime.now()),
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleProductRequestException(NoSuchElementException e) {
        log.error(DEFAULT_EXCEPTION_MESSAGE, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getName(), e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }
}