package com.exchangediary.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Service Exception
    INVALID_RANGE(HttpStatus.BAD_REQUEST, "유효하지 않은 범위입니다."),
    DUPLICATE(HttpStatus.BAD_REQUEST, "중복하는 데이터가 이미 존재합니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 자원입니다."),
    FAILED_TO_LOGIN_KAKAO(HttpStatus.INTERNAL_SERVER_ERROR, "kakao 로그인에 실패했습니다."),

    // Invalid Range
    INVALID_DATE_RANGE(HttpStatus.BAD_REQUEST, "유효하지 않은 날짜 범위입니다."),

    // Duplicate
    DIARY_DUPLICATED(HttpStatus.BAD_REQUEST, "오늘 일기는 작성 완료되었습니다."),

    // Not Found
    DIARY_NOT_FOUND(HttpStatus.NOT_FOUND, "일기를 찾을 수 없습니다."),
    UPLOAD_IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "일기 업로드 이미지를 찾을 수 없습니다."),

    // Kakao Login
    FAILED_TO_ISSUE_TOKEN(HttpStatus.INTERNAL_SERVER_ERROR, "kakao 토큰 발급에 실패했습니다."),
    FAILED_TO_GET_KAKAO_USER_INFO(HttpStatus.INTERNAL_SERVER_ERROR, "kakao 사용자 정보 조회에 실패했습니다."),

    // etc
    FAILED_UPLOAD_IMAGE(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드 중 오류가 발생했습니다.");

    private final HttpStatus statusCode;
    private final String message;
}
