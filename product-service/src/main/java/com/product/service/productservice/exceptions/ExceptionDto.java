package com.product.service.productservice.exceptions;

import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;



@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public record ExceptionDto(String message, HttpStatus httpStatus, LocalDateTime time) {
}
