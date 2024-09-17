package com.exchangediary.global.domain.entity;

import com.exchangediary.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ImageUploadException extends RuntimeException {
    private final ErrorCode errorCode;

    public ImageUploadException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }
}
