package com.example.unitTestStudy.common.exception.Handler;

import com.example.unitTestStudy.common.dto.ApiResponse;
import com.example.unitTestStudy.common.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ApiResponse<String> notFountExceptionHandler (NotFoundException e) {
        log.error("NotFountException", e);
        return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ApiResponse<String> exceptionHandler (Exception e) {
        log.error("Exception", e);
        return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
