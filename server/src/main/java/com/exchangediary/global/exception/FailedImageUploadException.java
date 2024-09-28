package com.exchangediary.global.exception;

public class FailedImageUploadException extends ServiceException {
    private String value;

    public FailedImageUploadException(String value) {
        this(value, ErrorCode.FAILED_UPLOAD_IMAGE);
        this.value = value;
    }

    public FailedImageUploadException(String value, ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
        this.value = value;
    }
}
