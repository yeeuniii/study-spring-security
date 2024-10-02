package com.exchangediary.global.exception.serviceexception;

import com.exchangediary.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {
    private final String value;
    private final ErrorCode errorCode;

    public ServiceException(String message, String value, ErrorCode errorCode) {
        super(message == null ? errorCode.getMessage() : message);
        this.value = value;
        this.errorCode = errorCode;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
