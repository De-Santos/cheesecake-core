package com.order.service.exceptions.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public record ExceptionDto(String message, HttpStatus httpStatus, LocalDateTime time) {
}
