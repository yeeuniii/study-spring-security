package com.exchangediary.global.exception;

import lombok.Getter;

@Getter
public class DuplicateException extends ServiceException {
    private String value;

    public DuplicateException(String value) {
        this(value, ErrorCode.INVALID_RANGE);
        this.value = value;
    }

    public DuplicateException(String value, ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
        this.value = value;
    }
}
