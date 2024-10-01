package com.exchangediary.global.exception.serviceexception.invliadrange;

import com.exchangediary.global.exception.ErrorCode;

public class DateRangeException extends InvalidRangeException {
    public DateRangeException(String value) {
        super(value, ErrorCode.INVALID_DATE_RANGE);
    }
}
