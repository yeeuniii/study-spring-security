package com.exchangediary.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INVALID_INPUT_BAD_REQUEST(HttpStatus.BAD_REQUEST, "입력이 유효하지 않습니다."),

    DIARY_NOT_FOUND(HttpStatus.NOT_FOUND, "일기를 찾을 수 없습니다."),
    STICKER_IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "스티커 이미지를 찾을 수 없습니다."),

    IMAGE_UPLOAD_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드 중 오류가 발생했습니다.");

    private final HttpStatus statusCode;
    private final String message;
}
