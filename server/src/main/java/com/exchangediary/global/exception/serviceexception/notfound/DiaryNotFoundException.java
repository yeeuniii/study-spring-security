package com.exchangediary.global.exception.serviceexception.notfound;

import com.exchangediary.global.exception.ErrorCode;

public class DiaryNotFoundException extends NotFoundException {
    public DiaryNotFoundException(String value) {
        super(value, ErrorCode.DIARY_NOT_FOUND);
    }
}
