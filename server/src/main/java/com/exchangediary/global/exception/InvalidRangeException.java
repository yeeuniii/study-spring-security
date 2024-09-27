package com.exchangediary.global.exception;

import lombok.Getter;

@Getter
public class InvalidRangeException extends ServiceException {
    private String value;

    public InvalidRangeException(String value) {
        this(value, ErrorCode.INVALID_RANGE);
        this.value = value;
    }

    public InvalidRangeException(String value, ErrorCode errorCode) {
        super(errorCode);
        this.value = value;
    }
}
