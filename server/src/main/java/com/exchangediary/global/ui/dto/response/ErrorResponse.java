package com.exchangediary.global.ui.dto.response;

import com.exchangediary.global.domain.entity.ErrorCode;
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
