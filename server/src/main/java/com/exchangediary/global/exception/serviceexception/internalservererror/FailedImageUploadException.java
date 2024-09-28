package com.exchangediary.global.exception.serviceexception.internalservererror;

import com.exchangediary.global.exception.ErrorCode;
import com.exchangediary.global.exception.serviceexception.ServiceException;

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
