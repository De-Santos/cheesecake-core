package com.order.service.exception.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ExceptionDto(String exceptionClass, String message, HttpStatus httpStatus, LocalDateTime time) {
}
