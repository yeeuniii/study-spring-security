package com.exchangediary.global.exception.serviceexception;

import com.exchangediary.global.exception.ErrorCode;

public class FailedImageUploadException extends ServiceException {
    public FailedImageUploadException(String message, String value, ErrorCode errorCode) {
        super(message, value, errorCode);
    }
}
