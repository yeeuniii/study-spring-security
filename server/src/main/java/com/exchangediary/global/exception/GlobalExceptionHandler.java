package com.exchangediary.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleInvalidArgumentException(MethodArgumentNotValidException exception) {
        try {
            return ApiErrorResponse.from(Objects.requireNonNull(exception.getFieldError()));
        } catch (NullPointerException e) {
            return ApiErrorResponse.from(HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(InvalidRangeException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleInvalidRangeException(InvalidDateException exception) {
        return ApiErrorResponse.of(exception.getErrorCode(), exception.getValue());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleNotFoundException(NotFoundException exception) {
        return ApiErrorResponse.of(exception.getErrorCode(), exception.getValue());
    }
}
