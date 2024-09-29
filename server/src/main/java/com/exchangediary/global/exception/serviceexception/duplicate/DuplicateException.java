package com.exchangediary.global.exception.serviceexception.duplicate;

import com.exchangediary.global.exception.ErrorCode;
import com.exchangediary.global.exception.serviceexception.ServiceException;
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
