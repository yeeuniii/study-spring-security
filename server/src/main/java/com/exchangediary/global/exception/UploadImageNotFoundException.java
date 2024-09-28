package com.exchangediary.global.exception;

public class UploadImageNotFoundException extends NotFoundException {
    public UploadImageNotFoundException(String value) {
        super(value, ErrorCode.UPLOAD_IMAGE_NOT_FOUND);
    }
}
