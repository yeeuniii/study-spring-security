package com.exchangediary.global.exception.serviceexception.invliadrange;

import com.exchangediary.global.exception.ErrorCode;
import com.exchangediary.global.exception.serviceexception.ServiceException;
import lombok.Getter;

@Getter
public class InvalidRangeException extends ServiceException {
    private String value;

    public InvalidRangeException(String value) {
        this(value, ErrorCode.INVALID_RANGE);
        this.value = value;
    }

    public InvalidRangeException(String value, ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
        this.value = value;
    }
}
