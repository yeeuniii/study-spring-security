package com.exchangediary.global.exception.serviceexception;

import com.exchangediary.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String value;

    public ServiceException(ErrorCode errorCode, String message, String value) {
        super(message == null ? errorCode.getMessage() : message);
        this.errorCode = errorCode;
        this.value = value;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
