package com.exchangediary.global.exception;

public class InvalidDateException extends InvalidRangeException {
    public InvalidDateException(String value) {
        super(value, ErrorCode.INVALID_DATE_RANGE);
    }
}
