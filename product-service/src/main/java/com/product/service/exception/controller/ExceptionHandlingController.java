package com.product.service.exception.controller;

import com.product.service.exception.dto.ExceptionDto;
import com.product.service.exception.exceptions.file.collection.main.FileCollectionException;
import com.product.service.exception.exceptions.file.collection.main.FileCollectionUpdateException;
import com.product.service.exception.exceptions.file.photo.main.FileException;
import com.product.service.exception.exceptions.file.photo.main.FileUpdateException;
import com.product.service.exception.exceptions.file.photo.main.NullFileException;
import com.product.service.exception.exceptions.product.main.DraftRequestException;
import com.product.service.exception.exceptions.product.main.ModifyingRequestException;
import com.product.service.exception.exceptions.product.main.ProductRequestException;
import com.product.service.exception.exceptions.tag.main.TagException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.cheesecake.dto.exception.PhotoNotFoundException;
import ua.cheesecake.dto.exception.ProductNotFoundException;

import java.sql.SQLException;
import java.time.LocalDateTime;

@Log4j2
@ControllerAdvice
public class ExceptionHandlingController {
    private static final String DEFAULT_EXCEPTION_MESSAGE = "Exception {}, exception message: {}";
    private static final String DEFAULT_EXCEPTION_LOG = "Exception: {} with message: {}";

    @ExceptionHandler(PhotoNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handlePhotoNotFoundException(PhotoNotFoundException e) {
        e.printStackTrace();
        log.error(DEFAULT_EXCEPTION_MESSAGE, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException e) {
        e.printStackTrace();
        log.error(DEFAULT_EXCEPTION_MESSAGE, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductRequestException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ExceptionDto> handleProductRequestException(final ProductRequestException e) {
        e.printStackTrace();
        log.error(DEFAULT_EXCEPTION_MESSAGE, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.NOT_ACCEPTABLE, LocalDateTime.now()),
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ModifyingRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleProductRequestException(ModifyingRequestException e) {
        e.printStackTrace();
        log.error(DEFAULT_EXCEPTION_MESSAGE, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ExceptionDto> handleException(FileException e) {
        e.printStackTrace();
        log.error(DEFAULT_EXCEPTION_LOG, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.NOT_ACCEPTABLE, LocalDateTime.now()),
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(FileUpdateException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ExceptionDto> handleException(FileUpdateException e) {
        e.printStackTrace();
        log.error(DEFAULT_EXCEPTION_LOG, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.NOT_ACCEPTABLE, LocalDateTime.now()),
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NullFileException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleException(NullFileException e) {
        e.printStackTrace();
        log.error(DEFAULT_EXCEPTION_LOG, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileCollectionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleException(FileCollectionException e) {
        e.printStackTrace();
        log.error(DEFAULT_EXCEPTION_LOG, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileCollectionUpdateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleException(FileCollectionUpdateException e) {
        e.printStackTrace();
        log.error(DEFAULT_EXCEPTION_LOG, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DraftRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleException(DraftRequestException e) {
        e.printStackTrace();
        log.error(DEFAULT_EXCEPTION_LOG, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TagException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleException(TagException e) {
        e.printStackTrace();
        log.error(DEFAULT_EXCEPTION_LOG, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionDto> handleException(SQLException e) {
        e.printStackTrace();
        log.error(DEFAULT_EXCEPTION_LOG, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionDto> handleException(RuntimeException e) {
        e.printStackTrace();
        log.error(DEFAULT_EXCEPTION_LOG, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


}