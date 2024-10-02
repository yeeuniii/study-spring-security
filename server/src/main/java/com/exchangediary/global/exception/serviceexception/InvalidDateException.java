package com.exchangediary.global.exception.serviceexception;

import com.exchangediary.global.exception.ErrorCode;

public class InvalidDateException extends ServiceException {
    public InvalidDateException(String message, String value, ErrorCode errorCode) {
        super(message, value, errorCode);
    }
}
