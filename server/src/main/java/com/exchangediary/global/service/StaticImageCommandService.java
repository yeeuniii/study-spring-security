package com.exchangediary.global.service;

import com.exchangediary.global.domain.entity.StaticImage;
import com.exchangediary.global.domain.entity.StaticImageType;
import com.exchangediary.global.exception.ErrorCode;
import com.exchangediary.global.exception.GlobalException;
import com.exchangediary.global.ui.dto.request.StaticImageUploadRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class StaticImageCommandService {
    private final ImageService imageService;

    public StaticImage createStaticImage(StaticImageUploadRequest request, MultipartFile image) {
        try {
            StaticImage saveImage = imageService.saveStaticImage(image, StaticImageType
                    .valueOf(request.type()));
            return saveImage;
        } catch (IOException e) {
            throw new GlobalException(ErrorCode.IMAGE_UPLOAD_ERROR);
        }
    }
}
