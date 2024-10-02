package com.exchangediary.global.exception.serviceexception;

import com.exchangediary.global.exception.ErrorCode;

public class NotFoundException extends ServiceException {
    private NotFoundException(ErrorCode errorCode, String message, String value) {
        super(errorCode, message, value);
    }
}
