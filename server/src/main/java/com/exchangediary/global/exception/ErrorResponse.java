package com.exchangediary.global.exception;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record ErrorResponse(
        HttpStatus statusCode,
        String message
) {
    public static ErrorResponse from(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .statusCode(errorCode.getStatusCode())
                .message(errorCode.getMessage())
                .build();
    }
}
