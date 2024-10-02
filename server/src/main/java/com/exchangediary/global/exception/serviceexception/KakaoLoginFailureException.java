package com.exchangediary.global.exception.serviceexception;

import com.exchangediary.global.exception.ErrorCode;

public class KakaoLoginFailureException extends ServiceException {
    public KakaoLoginFailureException(String message, String value, ErrorCode errorCode) {
        super(message, value, errorCode);
    }
}
