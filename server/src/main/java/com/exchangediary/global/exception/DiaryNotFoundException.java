package com.exchangediary.global.exception;

public class DiaryNotFoundException extends NotFoundException {
    public DiaryNotFoundException(String value) {
        super(value, ErrorCode.DIARY_NOT_FOUND);
    }
}
