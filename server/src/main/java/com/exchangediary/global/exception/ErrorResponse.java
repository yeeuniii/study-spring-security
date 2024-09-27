package com.exchangediary.global.exception;

import lombok.Builder;

@Builder
public record ErrorResponse(
        int statusCode,
        String message,
        String value
) {
    public static ErrorResponse from(ErrorCode errorCode, String value) {
        return ErrorResponse.builder()
                .statusCode(errorCode.getStatusCode().value())
                .message(errorCode.getMessage())
                .value(value)
                .build();
    }
}
