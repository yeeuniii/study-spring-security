package com.exchangediary.global.exception;

public class DateRangeException extends InvalidRangeException {
    public DateRangeException(String value) {
        super(value, ErrorCode.INVALID_DATE_RANGE);
    }
}
