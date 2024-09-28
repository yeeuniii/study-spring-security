package com.exchangediary.global.exception;

public class KakaoTokenFailureException extends KakaoLoginFailureException {
    public KakaoTokenFailureException(String value) {
        super(value, ErrorCode.FAILED_TO_ISSUE_TOKEN);
    }
}
