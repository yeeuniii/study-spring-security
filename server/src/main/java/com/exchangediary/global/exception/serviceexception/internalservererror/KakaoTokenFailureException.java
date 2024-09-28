package com.exchangediary.global.exception.serviceexception.internalservererror;

import com.exchangediary.global.exception.ErrorCode;

public class KakaoTokenFailureException extends KakaoLoginFailureException {
    public KakaoTokenFailureException(String value) {
        super(value, ErrorCode.FAILED_TO_ISSUE_TOKEN);
    }
}
