package com.exchangediary.global.exception.serviceexception;

import com.exchangediary.global.exception.ErrorCode;

public class NotFoundException extends ServiceException {
    private NotFoundException(String message, String value, ErrorCode errorCode) {
        super(message, value, errorCode);
    }
}
