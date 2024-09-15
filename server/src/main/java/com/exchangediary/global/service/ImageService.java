package com.exchangediary.global.domain.service;

import com.exchangediary.diary.domain.UploadImageRepository;
import com.exchangediary.diary.domain.entity.UploadImage;
import com.exchangediary.global.domain.StaticImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final StaticImageRepository staticImageRepository;
    private final UploadImageRepository uploadImageRepository;
}
