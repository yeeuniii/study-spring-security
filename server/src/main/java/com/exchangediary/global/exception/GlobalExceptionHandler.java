package com.exchangediary.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleInvalidArgumentException(Exception exception) {
        return makeErrorResponse(ErrorCode.INVALID_INPUT_BAD_REQUEST);
    }

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(GlobalException exception) {
        return makeErrorResponse(exception.getErrorCode());
    }

    private ResponseEntity<ErrorResponse> makeErrorResponse(ErrorCode errorCode) {
        ErrorResponse response = ErrorResponse.builder()
                .statusCode(errorCode.getStatusCode())
                .message(errorCode.getMessage())
                .build();

        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(response);
    }
}
