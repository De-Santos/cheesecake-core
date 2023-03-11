package com.product.service.productservice.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ExceptionDto(String message, HttpStatus httpStatus, LocalDateTime time) {
}
