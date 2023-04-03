package com.file.receiver.exceptions.handler;

import com.file.receiver.exceptions.dto.ExceptionDto;
import com.file.receiver.exceptions.exceptioins.file.FileException;
import com.file.receiver.exceptions.exceptioins.file.NullFileException;
import com.file.receiver.exceptions.exceptioins.found.FileNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@Log4j2
@ControllerAdvice
public class ExceptionHandlingController {
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
