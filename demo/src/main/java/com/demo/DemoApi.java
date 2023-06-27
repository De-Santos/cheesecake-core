package com.demo;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@SuppressWarnings("unused")
public interface DemoApi {

    @Operation(summary = "Test")
    @ResponseStatus(HttpStatus.OK)
    void test();

    @Operation(summary = "Fill database")
    @ResponseStatus(HttpStatus.OK)
    void fillDatabase();
}
