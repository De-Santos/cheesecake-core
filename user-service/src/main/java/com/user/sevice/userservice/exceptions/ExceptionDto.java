package com.user.sevice.userservice.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ExceptionDto(String message, HttpStatus httpStatus, LocalDateTime time) {
}
