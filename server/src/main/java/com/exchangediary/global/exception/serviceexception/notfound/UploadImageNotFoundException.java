package com.exchangediary.global.exception.serviceexception.notfound;

import com.exchangediary.global.exception.ErrorCode;

public class UploadImageNotFoundException extends NotFoundException {
    public UploadImageNotFoundException(String value) {
        super(value, ErrorCode.UPLOAD_IMAGE_NOT_FOUND);
    }
}
