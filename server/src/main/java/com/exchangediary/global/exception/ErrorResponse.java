package com.exchangediary.global.exception;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record ErrorResponse(
        HttpStatus statusCode,
        String message,
        String value
) {
    public static ErrorResponse from(ErrorCode errorCode, String value) {
        return ErrorResponse.builder()
                .statusCode(errorCode.getStatusCode())
                .message(errorCode.getMessage())
                .value(value)
                .build();
    }
}
