package com.exchangediary.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INVALID_RANGE(HttpStatus.BAD_REQUEST, "유효하지 않은 범위입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 자원입니다."),

    INVALID_DATE_RANGE(HttpStatus.BAD_REQUEST, "유효하지 않은 날짜 범위입니다."),

    DIARY_NOT_FOUND(HttpStatus.NOT_FOUND, "일기를 찾을 수 없습니다."),
    UPLOAD_IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "일기 업로드 이미지를 찾을 수 없습니다."),

    FAILED_UPLOAD_IMAGE(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드 중 오류가 발생했습니다.");

    private final HttpStatus statusCode;
    private final String message;
}
