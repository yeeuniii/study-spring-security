package com.exchangediary.global.exception.serviceexception.notfound;

import com.exchangediary.global.exception.ErrorCode;

public class GroupNotFoundException extends NotFoundException {
    public GroupNotFoundException(String value) {
        super(value, ErrorCode.GROUP_NOT_FOUND);
    }
}
