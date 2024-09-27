package com.exchangediary.global.exception;

public class DiaryDuplicateException extends DuplicateException {
    public DiaryDuplicateException(String value) {
        super(value, ErrorCode.DIARY_DUPLICATED);
    }
}
