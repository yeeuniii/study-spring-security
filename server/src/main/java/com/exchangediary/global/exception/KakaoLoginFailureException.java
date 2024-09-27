package com.exchangediary.global.exception;

import lombok.Getter;

@Getter
public class KakaoLoginFailureException extends ServiceException {
    private String value;

    public KakaoLoginFailureException(String value) {
        this(value, ErrorCode.FAILED_TO_LOGIN_KAKAO);
        this.value = value;
    }

    public KakaoLoginFailureException(String value, ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
        this.value = value;
    }
}
