package com.exchangediary.global.exception;

import lombok.Builder;

@Builder
public record ErrorResponse(
        int statusCode,
        String message
) {
    public static ErrorResponse from(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .statusCode(errorCode.getStatusCode())
                .message(errorCode.getMessage())
                .build();
    }
}
