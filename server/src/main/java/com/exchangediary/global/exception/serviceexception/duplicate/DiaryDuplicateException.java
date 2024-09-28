package com.exchangediary.global.exception.serviceexception.duplicate;

import com.exchangediary.global.exception.ErrorCode;

public class DiaryDuplicateException extends DuplicateException {
    public DiaryDuplicateException(String value) {
        super(value, ErrorCode.DIARY_DUPLICATED);
    }
}
