package com.exchangediary.global.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends ServiceException {
    private String value;

    public NotFoundException(String value) {
        this(value, ErrorCode.NOT_FOUND);
        this.value = value;
    }

    public NotFoundException(String value, ErrorCode errorCode) {
        super(errorCode);
        this.value = value;
    }
}
