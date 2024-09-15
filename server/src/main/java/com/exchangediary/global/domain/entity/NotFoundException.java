package com.exchangediary.global.domain.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotFoundException extends RuntimeException {
    private final ErrorCode errorCode;
}
