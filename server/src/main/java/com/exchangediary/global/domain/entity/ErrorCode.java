package com.exchangediary.global.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    DIARY_NOT_FOUND(404, "일기를 찾을 수 없습니다."),
    STICKER_IMAGE_NOT_FOUND(404, "스티커 이미지를 찾을 수 없습니다.");

    private final int statusCode;
    private final String message;
}
