package com.exchangediary.global.exception.serviceexception;

import com.exchangediary.global.exception.ErrorCode;

public class DuplicateException extends ServiceException {
    public DuplicateException(String message, String value, ErrorCode errorCode) {
        super(message, value, errorCode);
    }
}
