package com.exchangediary.diary.service;

import com.exchangediary.diary.domain.UploadImageRepository;
import com.exchangediary.diary.domain.entity.UploadImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UploadImageService {
    private final UploadImageRepository uploadImageRepository;

    @Transactional
    public UploadImage saveUploadImage(MultipartFile file) throws IOException {
        UploadImage image = UploadImage.builder()
                .image(file.getBytes())
                .build();

        return uploadImageRepository.save(image);
    }

    @Transactional(readOnly = true)
    public Optional<UploadImage> getUploadImage(Long id) {
        return uploadImageRepository.findById(id);
    }
}
