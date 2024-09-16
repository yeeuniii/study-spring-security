package com.exchangediary.global.ui;

import com.exchangediary.global.domain.entity.ErrorCode;
import com.exchangediary.global.domain.entity.NotFoundException;
import com.exchangediary.global.ui.dto.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleInvalidArgumentException(Exception exception) {
        return makeErrorResponse(ErrorCode.INVALID_INPUT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException exception) {
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
