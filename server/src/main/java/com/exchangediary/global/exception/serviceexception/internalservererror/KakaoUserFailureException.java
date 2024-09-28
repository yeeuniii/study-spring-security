package com.exchangediary.global.exception.serviceexception.internalservererror;

import com.exchangediary.global.exception.ErrorCode;

public class KakaoUserFailureException extends KakaoLoginFailureException {
    public KakaoUserFailureException(String value) {
        super(value, ErrorCode.FAILED_TO_GET_KAKAO_USER_INFO);
    }
}
