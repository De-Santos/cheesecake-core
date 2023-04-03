package com.file.receiver.exceptions.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ExceptionDto(String exceptionName, String message, HttpStatus httpStatus, LocalDateTime time) {
}
