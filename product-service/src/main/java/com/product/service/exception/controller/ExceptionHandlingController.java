package com.product.service.exception.controller;

import com.product.service.exception.dto.ExceptionDto;
import com.product.service.exception.exceptions.file.photo.found.FileNotFoundException;
import com.product.service.exception.exceptions.file.photo.main.FileException;
import com.product.service.exception.exceptions.file.photo.main.NullFileException;
import com.product.service.exception.exceptions.product.main.ModifyingRequestException;
import com.product.service.exception.exceptions.product.main.ProductRequestException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.cheesecake.dto.exception.PhotoNotFoundException;
import ua.cheesecake.dto.exception.ProductNotFoundException;

import java.time.LocalDateTime;

@Log4j2
@ControllerAdvice
public class ExceptionHandlingController {
    private static final String DEFAULT_EXCEPTION_MESSAGE = "Exception {}, exception message: {}";

    @ExceptionHandler(PhotoNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handlePhotoNotFoundException(PhotoNotFoundException e) {
        log.error(DEFAULT_EXCEPTION_MESSAGE, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException e) {
        log.error(DEFAULT_EXCEPTION_MESSAGE, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductRequestException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ExceptionDto> handleProductRequestException(final ProductRequestException e) {
        log.error(DEFAULT_EXCEPTION_MESSAGE, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.NOT_ACCEPTABLE, LocalDateTime.now()),
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ModifyingRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleProductRequestException(ModifyingRequestException e) {
        log.error(DEFAULT_EXCEPTION_MESSAGE, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    private static final String DEFAULT_EXCEPTION_LOG = "Exception: {} with message: {}";

    @ExceptionHandler(FileNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleException(FileNotFoundException e) {
        log.error(DEFAULT_EXCEPTION_LOG, e.getClass().getSimpleName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ExceptionDto> handleException(FileException e) {
        log.error(DEFAULT_EXCEPTION_LOG, e.getClass().getSimpleName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.NOT_ACCEPTABLE, LocalDateTime.now()),
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NullFileException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleException(NullFileException e) {
        log.error(DEFAULT_EXCEPTION_LOG, e.getClass().getSimpleName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }
}