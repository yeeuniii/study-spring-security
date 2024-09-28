package com.exchangediary.global.exception.serviceexception.internalservererror;

import com.exchangediary.global.exception.ErrorCode;
import com.exchangediary.global.exception.serviceexception.ServiceException;
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
