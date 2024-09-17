package com.exchangediary.global.service;

import com.exchangediary.global.domain.entity.StaticImage;
import com.exchangediary.global.domain.entity.StaticImageType;
import com.exchangediary.global.ui.dto.request.StaticImageUploadRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class StaticImageCommandService {
    private final ImageService imageService;

    public StaticImage createStaticImage(StaticImageUploadRequest request, MultipartFile image)
            throws IOException {
        try {
            StaticImage saveImage;
            if (image == null | image.isEmpty()) {
                // TODO: 지성이 코드 merge 받고 수정
//                throw new GlobalException(ErrorCode.);
            }
            saveImage = imageService.saveStaticImage(image, StaticImageType
                    .valueOf(request.type()));
            return saveImage;
        } catch (IOException e) {
            // TODO: 지성이 코드 merge 받고 수정
//                throw new ImageUploadException(ErrorCode.IMAGE_UPLOAD_ERROR, e);
        }
        return null;
    }
}
