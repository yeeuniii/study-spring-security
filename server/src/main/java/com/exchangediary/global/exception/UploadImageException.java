package com.exchangediary.global.exception;

public class UploadImageException extends ServiceException {
    private String value;

    public UploadImageException(String value) {
        this(value, ErrorCode.FAILED_UPLOAD_IMAGE);
        this.value = value;
    }

    public UploadImageException(String value, ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
        this.value = value;
    }
}
