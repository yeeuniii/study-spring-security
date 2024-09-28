package com.exchangediary.global.exception.serviceexception.notfound;

import com.exchangediary.global.exception.ErrorCode;
import com.exchangediary.global.exception.serviceexception.ServiceException;
import lombok.Getter;

@Getter
public class NotFoundException extends ServiceException {
    private String value;

    public NotFoundException(String value) {
        this(value, ErrorCode.NOT_FOUND);
        this.value = value;
    }

    public NotFoundException(String value, ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
        this.value = value;
    }
}
