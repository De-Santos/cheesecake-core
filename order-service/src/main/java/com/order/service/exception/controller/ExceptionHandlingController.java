package com.order.service.exception.controller;

import com.order.service.exception.dto.ExceptionDto;
import com.order.service.exception.exceptions.basket.main.BasketException;
import com.order.service.exception.exceptions.order.main.OrderException;
import com.order.service.exception.exceptions.order.main.OrderRequestException;
import com.order.service.exception.exceptions.order.main.RejectOrderRequestException;
import com.order.service.exception.exceptions.order.main.UpdateOrderRequestException;
import com.order.service.exception.exceptions.order.time.main.OrderTimeException;
import com.order.service.exception.exceptions.product.main.OrderProductException;
import com.order.service.exception.exceptions.user.main.UserException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;
import java.time.LocalDateTime;

@Log4j2
@ControllerAdvice
public class ExceptionHandlingController {
    private static final String DEFAULT_EXCEPTION_MESSAGE = "Exception {}, exception message: {}";

    @ExceptionHandler(BasketException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handlePhotoNotFoundException(BasketException e) {
        e.printStackTrace();
        log.error(DEFAULT_EXCEPTION_MESSAGE, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handlePhotoNotFoundException(OrderException e) {
        e.printStackTrace();
        log.error(DEFAULT_EXCEPTION_MESSAGE, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handlePhotoNotFoundException(OrderRequestException e) {
        e.printStackTrace();
        log.error(DEFAULT_EXCEPTION_MESSAGE, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RejectOrderRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handlePhotoNotFoundException(RejectOrderRequestException e) {
        e.printStackTrace();
        log.error(DEFAULT_EXCEPTION_MESSAGE, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UpdateOrderRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handlePhotoNotFoundException(UpdateOrderRequestException e) {
        e.printStackTrace();
        log.error(DEFAULT_EXCEPTION_MESSAGE, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderTimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handlePhotoNotFoundException(OrderTimeException e) {
        e.printStackTrace();
        log.error(DEFAULT_EXCEPTION_MESSAGE, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderProductException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handlePhotoNotFoundException(OrderProductException e) {
        e.printStackTrace();
        log.error(DEFAULT_EXCEPTION_MESSAGE, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handlePhotoNotFoundException(UserException e) {
        e.printStackTrace();
        log.error(DEFAULT_EXCEPTION_MESSAGE, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionDto> handlePhotoNotFoundException(SQLException e) {
        e.printStackTrace();
        log.error(DEFAULT_EXCEPTION_MESSAGE, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionDto> handlePhotoNotFoundException(RuntimeException e) {
        e.printStackTrace();
        log.error(DEFAULT_EXCEPTION_MESSAGE, e.getClass().getName(), e.getMessage());
        return new ResponseEntity<>(
                new ExceptionDto(e.getClass().getSimpleName(), e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
